import { useState, useCallback } from "react";
import { Message } from "@/types";
import { startNewRoom, loadMessages, checkRoom, sendMessageToApi } from "@/api/apiChat";
import axios from "axios";

const useChat = () => {
  const [messages, setMessages] = useState<Message[]>([]);
  const [isSending, setIsSending] = useState(false);
  const [uuid, setUuid] = useState<string | null>(null);

  /**
   * 새로운 채팅 방을 초기화합니다.
   */
  const initializeNewRoom = useCallback(async () => {
    try {
      const newUuid = await startNewRoom();
      localStorage.setItem("uuid", newUuid);
      setUuid(newUuid);
    } catch (error) {
      console.error("방을 시작하지 못했습니다:", error);
    }
  }, []);

  /**
   * 특정 방의 메시지를 불러옵니다.
   * @param uuid 방의 UUID
   */
  const fetchMessages = useCallback(async (uuid: string) => {
    try {
      const loadedMessages = await loadMessages(uuid);
      setMessages(loadedMessages);
    } catch (error) {
      console.error("메시지를 불러오지 못했습니다:", error);
    }
  }, []);

  /**
   * 특정 방의 존재 여부를 확인하고, 존재하지 않으면 새 방을 초기화합니다.
   * @param uuid 방의 UUID
   */
  const verifyRoom = useCallback(
    async (uuid: string) => {
      try {
        const roomName = await checkRoom(uuid);
        if (roomName) {
          setUuid(uuid);
          await fetchMessages(uuid);
        } else {
          await initializeNewRoom();
        }
      } catch (error) {
        console.error("방을 확인하지 못했습니다:", error);
        await initializeNewRoom();
      }
    },
    [fetchMessages, initializeNewRoom],
  );

  /**
   * 입력된 메시지를 전송하고 봇의 응답을 처리합니다.
   * @param inputValue 전송할 메시지 내용
   */
  const sendMessage = useCallback(
    async (inputValue: string) => {
      if (inputValue.trim() !== "" && !isSending && uuid) {
        const newMessage: Message = {
          content: inputValue,
          createdTime: new Date(),
          isUser: true,
        };

        const pendingMessage: Message = {
          content: "답변을 생성중입니다.",
          createdTime: new Date(),
          isUser: false,
          isPending: true,
        };

        setMessages((prevMessages) => [...prevMessages, newMessage, pendingMessage]);
        setIsSending(true);

        try {
          const botMessage = await sendMessageToApi(uuid, newMessage);
          setMessages((prevMessages) =>
            prevMessages.map((msg) => (msg.isPending ? botMessage : msg)),
          );
        } catch (error) {
          if (axios.isAxiosError(error) && error.response) {
            const errorMessage: Message = {
              content: error.response.data.content,
              createdTime: new Date(error.response.data.createdTime),
              isUser: false,
            };
            setMessages((prevMessages) =>
              prevMessages.map((msg) => (msg.isPending ? errorMessage : msg)),
            );
          } else {
            console.error("오류가 발생했습니다:", error);
            const errorMessage: Message = {
              createdTime: new Date(),
              content: "응답을 받을 수 없습니다.",
              isUser: false,
            };
            setMessages((prevMessages) =>
              prevMessages.map((msg) => (msg.isPending ? errorMessage : msg)),
            );
          }
        } finally {
          setIsSending(false);
        }
      }
    },
    [isSending, uuid],
  );

  return {
    messages,
    isSending,
    checkRoom: verifyRoom,
    startNewRoom: initializeNewRoom,
    sendMessage,
  };
};

export default useChat;

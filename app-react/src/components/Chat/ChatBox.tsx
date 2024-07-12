import React, { useEffect, useRef, useCallback } from "react";
import { ChatInput, ChatWindow, ChatHeader } from "@/components/Chat";
import useChat from "@/hooks/useChat";

interface ChatBoxProps {
  /** 채팅창을 토글하는 함수 */
  toggleChat: () => void;
}

/**
 * 채팅창 전체를 감싸는 컴포넌트입니다.
 * @param toggleChat 채팅창을 여닫는 함수
 */
const ChatBox: React.FC<ChatBoxProps> = ({ toggleChat }) => {
  const { messages, checkRoom, startNewRoom, sendMessage, isSending } = useChat();
  const messagesEndRef = useRef<HTMLDivElement | null>(null);

  /**
   * 채팅창의 스크롤을 가장 아래로 내립니다.
   */
  const scrollToBottom = useCallback(() => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, []);

  useEffect(() => {
    /**
     * 채팅을 초기화합니다.
     */
    const initializeChat = async () => {
      const storedUuid = localStorage.getItem("uuid");
      if (storedUuid) {
        await checkRoom(storedUuid);
      } else {
        await startNewRoom();
      }
    };

    initializeChat().catch((error) => {
      console.error("채팅 초기화 실패:", error);
    });
  }, [checkRoom, startNewRoom]);

  useEffect(() => {
    scrollToBottom();
  }, [messages, scrollToBottom]);

  return (
    <div className="artboard phone-3 fixed bottom-24 right-4 z-50 flex flex-col rounded-lg border border-gray-300 bg-white shadow-lg">
      <ChatHeader toggleChat={toggleChat} />
      <ChatWindow messages={messages} messagesEndRef={messagesEndRef} />
      <ChatInput sendMessage={sendMessage} isSending={isSending} />
    </div>
  );
};

export default ChatBox;

import React, { useState, useEffect, useRef, useCallback } from "react";
import axios from "axios";
import { LiaRobotSolid } from "react-icons/lia";
import { IoMdClose } from "react-icons/io";
import { IoSend } from "react-icons/io5"; // 전송 버튼 아이콘 추가
import "@/styles/App.css"; // CSS 파일을 불러옵니다

interface Message {
  text: string;
  timestamp: Date;
  isUser: boolean;
  isPending?: boolean;
}

const App: React.FC = () => {
  const [isChatOpen, setIsChatOpen] = useState(false);
  const [messages, setMessages] = useState<Message[]>([]);
  const [inputValue, setInputValue] = useState("");
  const [isSending, setIsSending] = useState(false);
  const [uuid, setUuid] = useState<string | null>(null);
  const messagesEndRef = useRef<HTMLDivElement | null>(null);

  const toggleChat = () => {
    setIsChatOpen(!isChatOpen);
  };

  const startNewRoom = async () => {
    try {
      const response = await axios.post("/app-springboot/api/room/start");
      const newUuid = response.data.roomName;
      localStorage.setItem("uuid", newUuid);
      setUuid(newUuid);
    } catch (error) {
      console.error("Failed to start new room:", error);
    }
  };

  const loadMessages = async (uuid: string) => {
    try {
      const response = await axios.get(`/app-springboot/api/message/${uuid}`);
      const loadedMessages = response.data.map((msg: any) => ({
        text: msg.content,
        timestamp: new Date(msg.createdTime),
        isUser: msg.isUser,
      }));
      setMessages(loadedMessages);
    } catch (error) {
      console.error("Failed to load messages:", error);
    }
  };

  const checkRoom = useCallback(async (uuid: string) => {
    try {
      const response = await axios.get(`/app-springboot/api/room/${uuid}`);
      if (response.data && response.data.roomName) {
        setUuid(uuid);
        await loadMessages(uuid);
      } else {
        await startNewRoom();
      }
    } catch (error) {
      console.error("Room check failed:", error);
      await startNewRoom();
    }
  }, []);

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  const handleSendMessage = async () => {
    if (inputValue.trim() !== "" && !isSending && uuid) {
      const newMessage: Message = {
        text: inputValue,
        timestamp: new Date(),
        isUser: true,
      };

      setMessages([...messages, newMessage]);
      setInputValue("");
      setIsSending(true);

      const pendingMessage: Message = {
        text: "답변을 생성중입니다.",
        timestamp: new Date(),
        isUser: false,
        isPending: true,
      };

      setMessages((prevMessages) => [...prevMessages, pendingMessage]);

      try {
        const response = await axios.post(`/app-springboot/api/chat/${uuid}`, {
          isUser: true,
          content: newMessage.text,
          createdTime: newMessage.timestamp.toISOString(),
        });

        const botMessage: Message = {
          text: response.data.content,
          timestamp: new Date(response.data.createdTime),
          isUser: false,
        };

        setMessages((prevMessages) =>
          prevMessages.map((msg) => (msg.isPending ? botMessage : msg)),
        );
      } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
          const botMessage: Message = {
            text: error.response.data.content,
            timestamp: new Date(error.response.data.createdTime),
            isUser: false,
          };

          setMessages((prevMessages) =>
            prevMessages.map((msg) => (msg.isPending ? botMessage : msg)),
          );
        } else {
          setMessages((prevMessages) =>
            prevMessages.map((msg) =>
              msg.isPending ? { ...msg, text: "응답을 받을 수 없습니다.", isPending: false } : msg,
            ),
          );
        }
      } finally {
        setIsSending(false);
      }
    }
  };

  const handleKeyDown = async (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      await handleSendMessage();
    }
  };

  const scrollToBottom = () => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: "smooth" });
    }
  };

  useEffect(() => {
    const initializeChat = async () => {
      const storedUuid = localStorage.getItem("uuid");
      if (storedUuid) {
        await checkRoom(storedUuid);
      } else {
        await startNewRoom();
      }
    };

    initializeChat().catch((error) => {
      console.error("Failed to initialize chat:", error);
    });
  }, [checkRoom]);

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const formatDate = (date: Date) => {
    return date.toLocaleDateString();
  };

  const formatTime = (date: Date) => {
    return date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
  };

  const shouldShowDate = (currentMessage: Message, previousMessage: Message | null) => {
    if (!previousMessage) return true;
    return formatDate(currentMessage.timestamp) !== formatDate(previousMessage.timestamp);
  };

  const shouldShowTime = (currentMessage: Message, nextMessage: Message | null) => {
    if (!nextMessage) return true;
    return formatTime(currentMessage.timestamp) !== formatTime(nextMessage.timestamp);
  };

  return (
    <div className="relative">
      {/* 플로팅 버튼 */}
      <button
        onClick={toggleChat}
        className="fixed bottom-4 right-4 z-50 h-12 w-12 rounded-lg bg-blue-500 text-white shadow-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
      >
        {isChatOpen ? "X" : "💬"}
      </button>

      {/* 채팅창 */}
      {isChatOpen && (
        <div className="artboard phone-3 fixed bottom-20 right-4 z-50 flex flex-col rounded-lg border border-gray-300 bg-white shadow-lg">
          <div className="flex items-center justify-between border-b border-gray-200 p-4">
            <h2 className="flex items-center text-lg font-semibold">
              <LiaRobotSolid className="mr-2" />
              인하공업전문대학 입학상담
            </h2>
            <button
              onClick={toggleChat}
              className="flex h-8 w-8 items-center justify-center rounded border border-gray-300 bg-gray-100 text-gray-600 hover:text-gray-900 focus:outline-none"
            >
              <IoMdClose className="h-4 w-4" />
            </button>
          </div>
          <div className="hide-scrollbar flex-1 space-y-2 overflow-y-auto p-4">
            {messages.map((message, index) => (
              <div key={index}>
                {shouldShowDate(message, messages[index - 1] || null) && (
                  <div className="mb-2 text-center text-xs text-gray-500">
                    {formatDate(message.timestamp)}
                  </div>
                )}
                <div className={`flex flex-col ${message.isUser ? "items-end" : "items-start"}`}>
                  <div
                    className={`w-auto max-w-xs rounded-lg p-2 text-white ${message.isUser ? "bg-blue-500" : "bg-gray-300"}`}
                  >
                    <p className="text-left">{message.text}</p>
                  </div>
                  {shouldShowTime(message, messages[index + 1] || null) && (
                    <p className="mt-1 text-xs text-gray-500">{formatTime(message.timestamp)}</p>
                  )}
                </div>
              </div>
            ))}
            <div ref={messagesEndRef} />
          </div>
          <div className="flex items-center space-x-2 rounded-b-lg border-t border-gray-200 bg-gray-100 p-2">
            <input
              type="text"
              value={inputValue}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
              placeholder="정확한 답변을 위해서 학과명 등을 올바르게 입력해주세요."
              className="flex-1 rounded-l-md border-none bg-white px-4 py-2 focus:outline-none"
              disabled={isSending}
            />
            <button
              onClick={handleSendMessage}
              className="flex h-10 w-10 items-center justify-center rounded-r-md bg-blue-500 text-white hover:bg-blue-600 focus:outline-none"
              disabled={isSending}
            >
              <IoSend className="h-4 w-4" />
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default App;

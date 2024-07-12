import React, { useEffect } from "react";
import { ChatMessage } from "@/components/Chat";
import { Message } from "@/types";

interface ChatWindowProps {
  /** 메시지 배열 */
  messages: Message[];
  /** 메시지 끝 부분을 참조하는 ref */
  messagesEndRef: React.RefObject<HTMLDivElement>;
}

/**
 * 채팅 메시지들을 표시하는 창 컴포넌트입니다.
 * @param messages 표시할 메시지 배열
 * @param messagesEndRef 메시지 끝 부분을 참조하는 ref
 */
const ChatWindow: React.FC<ChatWindowProps> = ({ messages, messagesEndRef }) => {
  useEffect(() => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages, messagesEndRef]);

  return (
    <div className="hide-scrollbar flex-1 space-y-2 overflow-y-auto p-4">
      {messages.map((message, index) => (
        <ChatMessage
          key={index}
          message={message}
          previousMessage={messages[index - 1] || null}
          nextMessage={messages[index + 1] || null}
        />
      ))}
      <div ref={messagesEndRef} />
    </div>
  );
};

export default ChatWindow;

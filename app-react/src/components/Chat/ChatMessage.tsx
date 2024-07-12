import React from "react";
import { Message } from "@/types";
import { formatDate, formatTime } from "@/utils/utilsDate";

interface ChatMessageProps {
  /** 메시지 객체 */
  message: Message;
  /** 이전 메시지 객체 (없을 수도 있음) */
  previousMessage?: Message;
  /** 다음 메시지 객체 (없을 수도 있음) */
  nextMessage?: Message;
}

/**
 * 채팅 메시지를 표시하는 컴포넌트입니다.
 * @param message 현재 메시지 객체
 * @param previousMessage 이전 메시지 객체 (옵션)
 * @param nextMessage 다음 메시지 객체 (옵션)
 */
const ChatMessage: React.FC<ChatMessageProps> = React.memo(
  ({ message, previousMessage, nextMessage }) => {
    /**
     * 날짜를 표시해야 하는지 여부를 결정합니다.
     * @param currentMessage 현재 메시지 객체
     * @param previousMessage 이전 메시지 객체 (옵션)
     * @returns 날짜를 표시해야 하는지 여부
     */
    const shouldShowDate = (currentMessage: Message, previousMessage?: Message) => {
      if (!previousMessage) return true;
      return formatDate(currentMessage.createdTime) !== formatDate(previousMessage.createdTime);
    };

    /**
     * 시간을 표시해야 하는지 여부를 결정합니다.
     * @param currentMessage 현재 메시지 객체
     * @param nextMessage 다음 메시지 객체 (옵션)
     * @returns 시간을 표시해야 하는지 여부
     */
    const shouldShowTime = (currentMessage: Message, nextMessage?: Message) => {
      if (!nextMessage) return true;
      return formatTime(currentMessage.createdTime) !== formatTime(nextMessage.createdTime);
    };

    return (
      <div>
        {shouldShowDate(message, previousMessage) && (
          <div className="mb-2 text-center text-xs text-gray-500">
            {formatDate(message.createdTime)}
          </div>
        )}
        <div className={`flex flex-col ${message.isUser ? "items-end" : "items-start"}`}>
          <div
            className={`w-auto max-w-xs rounded-lg p-2 text-white ${
              message.isUser ? "bg-blue-500" : "bg-gray-300"
            }`}
          >
            <p className="text-left">{message.content}</p>
          </div>
          {shouldShowTime(message, nextMessage) && (
            <p className="mt-1 text-xs text-gray-500">{formatTime(message.createdTime)}</p>
          )}
        </div>
      </div>
    );
  },
);

ChatMessage.displayName = "ChatMessage";

export default ChatMessage;

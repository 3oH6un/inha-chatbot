import React, { useState } from "react";
import { IoSend } from "react-icons/io5";

interface ChatInputProps {
  /** 메시지를 전송하는 함수 */
  sendMessage: (message: string) => void;
  /** 메시지 전송 중 여부 */
  isSending: boolean;
}

/**
 * 채팅 입력창 컴포넌트입니다.
 * @param sendMessage 메시지를 전송하는 함수
 * @param isSending 메시지 전송 중 여부
 */
const ChatInput: React.FC<ChatInputProps> = ({ sendMessage, isSending }) => {
  const [inputValue, setInputValue] = useState("");

  /**
   * 입력 필드의 값이 변경될 때 호출되는 함수입니다.
   * @param event 입력 필드 변경 이벤트
   */
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  /**
   * 메시지를 전송하는 함수입니다.
   */
  const handleSendMessage = async () => {
    if (inputValue.trim() !== "") {
      await sendMessage(inputValue);
      setInputValue("");
    }
  };

  /**
   * 키다운 이벤트를 처리하는 함수입니다.
   * @param event 키다운 이벤트
   */
  const handleKeyDown = async (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      await handleSendMessage();
    }
  };

  return (
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
  );
};

export default ChatInput;

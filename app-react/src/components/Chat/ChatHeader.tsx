import React from "react";
import { LiaRobotSolid } from "react-icons/lia";
import { IoMdClose } from "react-icons/io";

interface ChatHeaderProps {
  /** 채팅창을 토글하는 함수 */
  toggleChat: () => void;
}

/**
 * 채팅창의 헤더를 나타내는 컴포넌트입니다.
 * @param toggleChat 채팅창을 여닫는 함수
 */
const ChatHeader: React.FC<ChatHeaderProps> = ({ toggleChat }) => {
  return (
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
  );
};

export default ChatHeader;

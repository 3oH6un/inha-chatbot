import React from "react";
import { IoMdClose } from "react-icons/io";
import { IoChatboxEllipsesOutline } from "react-icons/io5";

interface FloatingButtonProps {
  /** 채팅창이 열려 있는지 여부 */
  isChatOpen: boolean;
  /** 채팅창을 여닫는 함수 */
  toggleChat: () => void;
}

/**
 * 채팅창을 여닫는 플로팅 버튼 컴포넌트입니다.
 * @param isChatOpen 채팅창이 열려 있는지 여부
 * @param toggleChat 채팅창을 여닫는 함수
 */
const FloatingButton: React.FC<FloatingButtonProps> = ({ isChatOpen, toggleChat }) => {
  return (
    <button
      onClick={toggleChat}
      className="fixed bottom-4 right-4 z-50 flex h-16 w-16 items-center justify-center rounded-lg bg-blue-500 text-white shadow-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
    >
      {isChatOpen ? (
        <IoMdClose className="h-12 w-12" />
      ) : (
        <IoChatboxEllipsesOutline className="h-12 w-12" />
      )}
    </button>
  );
};

export default FloatingButton;

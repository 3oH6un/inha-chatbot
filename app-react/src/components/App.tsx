import React, { useState } from "react";
import { ChatBox } from "@/components/Chat";
import { FloatingButton } from "@/components/FloatingButton";

/**
 * 애플리케이션의 루트 컴포넌트입니다.
 */
const App: React.FC = () => {
  const [isChatOpen, setIsChatOpen] = useState(false);

  /**
   * 채팅창을 토글하는 함수입니다.
   */
  const toggleChat = () => {
    setIsChatOpen(!isChatOpen);
  };

  return (
    <div className="relative">
      <FloatingButton isChatOpen={isChatOpen} toggleChat={toggleChat} />
      {isChatOpen && <ChatBox toggleChat={toggleChat} />}
    </div>
  );
};

export default App;

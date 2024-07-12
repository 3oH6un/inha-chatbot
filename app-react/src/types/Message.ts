/**
 * 채팅 메시지를 나타내는 인터페이스입니다.
 */
export interface Message {
  /** 메시지의 내용 */
  content: string;
  /** 메시지가 생성된 시간 */
  createdTime: Date;
  /** 메시지가 사용자가 보낸 것인지 여부 */
  isUser: boolean;
  /** (선택 사항) 메시지가 대기 중인지 여부를 나타내는 플래그 */
  isPending?: boolean;
}

import axios from "axios";
import { Message } from "@/types";

const apiBaseUrl: string = "/app-springboot/api";

/**
 * 새로운 채팅 방을 시작합니다.
 * @returns 생성된 방의 UUID
 */
export const startNewRoom = async (): Promise<string> => {
  const response = await axios.post(`${apiBaseUrl}/room/start`);
  return response.data.roomName;
};

/**
 * 특정 방의 메시지 목록을 불러옵니다.
 * @param uuid 방의 UUID
 * @returns 메시지 배열
 */
export const loadMessages = async (uuid: string): Promise<Message[]> => {
  const response = await axios.get(`${apiBaseUrl}/message/${uuid}`);
  return response.data.map((msg: any) => ({
    content: msg.content,
    createdTime: new Date(msg.createdTime),
    isUser: msg.isUser,
  })) as Message[];
};

/**
 * 특정 방의 존재 여부를 확인합니다.
 * @param uuid 방의 UUID
 * @returns 방의 UUID (방이 존재하는 경우)
 */
export const checkRoom = async (uuid: string): Promise<string> => {
  const response = await axios.get(`${apiBaseUrl}/room/${uuid}`);
  return response.data.roomName;
};

/**
 * 특정 방에 메시지를 전송합니다.
 * @param uuid 방의 UUID
 * @param message 전송할 메시지
 * @returns 봇의 응답 메시지
 */
export const sendMessageToApi = async (uuid: string, message: Message): Promise<Message> => {
  const response = await axios.post(`${apiBaseUrl}/chat/${uuid}`, message);
  return {
    content: response.data.content,
    createdTime: new Date(response.data.createdTime),
    isUser: false,
  } as Message;
};

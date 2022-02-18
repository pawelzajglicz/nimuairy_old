import { User } from 'src/app/model/user';
import { Message } from "./message";

export interface Conversation {
  conversationId: number;
  participants: User[];
  messages: Message[];
  totalMessages: number;
}

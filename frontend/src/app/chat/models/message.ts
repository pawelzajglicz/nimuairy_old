export interface Message {
  content: string;
  conversationId: number;
  id: number;
  senderId: number;
  sentAt: Date;
}

export interface Message {
  content: string;
  conversationId: number;
  id: number;
  orderNumber: number;
  senderId: number;
  sentAt: Date;
}

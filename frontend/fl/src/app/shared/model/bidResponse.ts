import { userResponse } from './userResponse';

export interface bidResponse {
  bidId: number;
  projectId: number;
  freelancerId: number;
  amount: number;
  deliveryDays: number;
  description: string;
  status: string;
  createdDate: string;
  freelacerDetails: userResponse;
}

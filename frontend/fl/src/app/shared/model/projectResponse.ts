import { skillResponse } from './skillResponse';
import { bidResponse } from './bidResponse';

export interface ProjectResponse {
  projectId: number;
  clientId: number;
  projectName: String;
  projectDescription: String;
  isConfidential: boolean;
  paymentTypeId: number;
  bidStartDate: Date;
  bidEndDate: Date;
  minPrice: number;
  maxPrice: number;
  createdDate: Date;
  status: String;
  skills: skillResponse[];
  bids: bidResponse[];
}

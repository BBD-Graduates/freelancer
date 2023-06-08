export interface ProjectModel {
  clientId: number;
  projectName: string;
  projectDescription: string;
  isConfidential: boolean;
  bidStartDate: Date;
  bidEndDate: Date;
  minPrice: number;
  maxPrice: number;
  skillIds: any[];
}

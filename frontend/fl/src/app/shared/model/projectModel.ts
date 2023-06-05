export interface ProjectModel {
  projectName: string;
  projectDescription: string;
  isConfidential: boolean;
  bidStartDate: Date;
  bidEndDate: Date;
  minPrice: number;
  maxPrice: number;
  status: string;
}

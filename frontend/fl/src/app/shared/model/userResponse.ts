import { LanguageResponse } from './languageResponse';
import { RatingResponse } from './ratingResponse';
import { skillResponse } from './skillResponse';

export interface userResponse {
  userId?: number;
  firstName?: string;
  lastName?: string;
  headLine?: string;
  summary?: string;
  company?: string;
  email?: string;
  phNo?: string;
  isVerified?: boolean;
  photoUrl?: string;
  userRole?: string;
  createdDate?: Date;
  stateName?: string;
  countryName?: string;
  languages?: LanguageResponse[];
  skills?: skillResponse[];
  ratings?: RatingResponse[];
}

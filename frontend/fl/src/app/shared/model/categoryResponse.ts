import { skillResponse } from "./skillResponse"

export interface categoryResponse {
    categoryId: number,
    categoryName: string,
    logoURl: number,
    skills:skillResponse[]
}
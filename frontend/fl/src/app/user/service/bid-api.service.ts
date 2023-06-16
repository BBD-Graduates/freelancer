import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { config } from 'src/app/config';
import { ApiResponse } from 'src/app/shared/model/apiResponse';
import { bidResponse } from 'src/app/shared/model/bidResponse';
import { ProjectResponse } from 'src/app/shared/model/projectResponse';

@Injectable({
  providedIn: 'root',
})
export class BidApiService {
  constructor(private http: HttpClient, private router: Router) {}

  data: any = [];
  async getBids({
    bidId,
    projectId,
    freelancerId,
    status,
  }: {
    bidId?: number;
    projectId?: number;
    freelancerId?: number;
    status?: String;
  }): Promise<bidResponse[] | null> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'bidId', bidId);
      params = this.addParamsIfNotEmpty(params, 'projectId', projectId);
      params = this.addParamsIfNotEmpty(params, 'freelancerId', freelancerId);
      params = this.addParamsIfNotEmpty(params, 'status', status);
      const options = { params: params };
      const bidResponse = await this.http
        .get(config.BidApi.insertBid, options)
        .toPromise();

      const data = bidResponse as ApiResponse;
      if (data.response != null) {
        const bids = data.response as bidResponse[];
        return bids;
      } else {
        return null;
      }
    } catch (error) {
      console.error('GetBidsApi_ERROR', error);
      throw error;
    }
  }

  async updateBidStatusToApprove({
    bidId,
    projectId,
  }: {
    bidId?: number;
    projectId?: number;
  }): Promise<ApiResponse> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'bidId', bidId);
      params = this.addParamsIfNotEmpty(params, 'projectId', projectId);
      const options = { params: params };
      console.log('bidservice', options);
      const status = await this.http
        .get(config.BidApi.updateBid, options)
        .toPromise();
      const bidApproveStatus = status as ApiResponse;
      return bidApproveStatus;
    } catch (error) {
      console.error('approveBids_ERROR', error);
      throw error;
    }
  }

  addParamsIfNotEmpty(params: any, key: string, value: any): any {
    if (value !== null && value !== undefined && value !== '') {
      params = params.set(key, value);
    }
    return params;
  }
  insertBid(data: any) {
    return this.http.post(config.BidApi.insertBid, data);
  }
  updatetBid(data: any,bidId:number) {
    return this.http.put(config.BidApi.insertBid+"/"+bidId, data);
  }
}

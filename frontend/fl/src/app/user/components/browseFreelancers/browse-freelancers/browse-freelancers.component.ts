import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserapiService } from 'src/app/user/service/user-api.service';

@Component({
  selector: 'fl-browse-freelancers',
  templateUrl: './browse-freelancers.component.html',
  styleUrls: ['./browse-freelancers.component.css'],
})
export class BrowseFreelancersComponent {
  constructor(private userApiService: UserapiService, private router: Router) {}
  userDetails:
    | {
        userId: number;
        profileUrl: any;
        firstName: any;
        lastName: any;
        headLine: any;
        summary: any;
        company: any;
        email: any;
        phNo: any;
        createdDate: any;
        state: any;
        country: any;
        ratings: any;
        skills: any;
        languages: any;
      }
    | undefined;
  userDetailsList: any[] = [];
  userData: any;

  async ngOnInit(): Promise<void> {
    this.userData = await this.userApiService.getAllUsers({});
    const res = this.userData?.response;

    res.forEach((userData: any) => {
      const response = userData || {};
      this.userDetails = {
        userId: response.userId || '',
        profileUrl: response['photoUrl']?.replace('s96-c', 's400-c') || '',
        firstName: response['firstName']?.toUpperCase() || '',
        lastName: response['lastName']?.toUpperCase() || '',
        headLine: response['headLine'] || '',
        summary: response['summary'] || '',
        company: response['company'] || '',
        email: response['email'] || '',
        phNo: response['phNo'] || '',
        createdDate: response['createdDate'] || '',
        state: response['stateName'] || '',
        country: response['countryName'] || '',
        ratings: response['ratings'] || [],
        skills: response['skills'] || [],
        languages: response['languages'] || [],
      };
      this.userDetailsList.push(this.userDetails);
    });
  }
}

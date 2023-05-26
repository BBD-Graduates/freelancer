import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserapiService } from 'src/app/user/service/user-api.service';

@Component({
  selector: 'fl-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent {
  constructor(private userapiService: UserapiService, private router: Router) {}
  ngOnInit() {
    this.getUserDetails();
  }
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
  joiningDate: any;
  dateString: any;
  photoUrl: any;

  sessionStatus = sessionStorage.getItem('userEmail') == null;

  async getUserDetails() {
    let userEmail = sessionStorage.getItem('userEmail') ?? '';
    const user = await this.userapiService.getAllUsers({
      email: userEmail,
    });
    this.profileUrl = user?.response[0]['photoUrl'];
    this.firstName = user?.response[0]['firstName'].toUpperCase();
    this.lastName = user?.response[0]['lastName'].toUpperCase();
    this.headLine = user?.response[0]['headLine'];
    this.summary = user?.response[0]['summary'];
    this.company = user?.response[0]['company'];
    this.email = user?.response[0]['email'];
    this.phNo = user?.response[0]['phNo'];
    this.createdDate = user?.response[0]['createdDate'];
    this.state = user?.response[0]['stateName'];
    this.country = user?.response[0]['countryName'];
    this.photoUrl = this.profileUrl.replace('s96-c', 's400-c');
  }

  showDate(dateTimeString: any) {
    const date = new Date(dateTimeString);
    const formattedDate = date.toLocaleString('en-US', {
      month: 'long',
      day: 'numeric',
      year: 'numeric',
    });
    return formattedDate;
  }
}

import { Component } from '@angular/core';
import { UserapiService } from '../../service/user-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'fl-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  constructor(private userapiService: UserapiService, private router: Router) {}
  ngOnInit() {
    this.getUserDetails();
  }

  profileUrl!: string;
  firstName!: string;
  lastName!: string;
  headLine!: string;
  summary!: string;
  company!: string;
  email!: string;
  phNo!: number;
  createdDate!: Date;
  state!: string;
  country!: string;
  joiningDate!: Date;
  dateString!: string;
  photoUrl!: string;

  ratings: any[] = [];
  skills: any[] = [];
  languages: any[] = [];

  sessionStatus = localStorage.getItem('userEmail') == null;

  async getUserDetails() {
    let userEmail = localStorage.getItem('userEmail') ?? '';
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
    this.ratings = user?.response[0]['ratings'];
    this.skills = user?.response[0]['skills'];
    this.languages = user?.response[0]['languages'];
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

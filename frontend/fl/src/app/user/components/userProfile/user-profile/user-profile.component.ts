import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserapiService } from 'src/app/user/service/user-api.service';

@Component({
  selector: 'fl-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent {
  constructor(
    private userapiService: UserapiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  id!: any;
  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('userId');
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

  async getUserDetails() {
    const user = await this.userapiService.getAllUsers({
      userId: this.id,
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

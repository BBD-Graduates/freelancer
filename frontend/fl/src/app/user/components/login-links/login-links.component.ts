import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserapiService } from '../../service/user-api.service';
import { ApiResponse } from 'src/app/shared/model/apiResponse';

@Component({
  selector: 'fl-login-links',
  templateUrl: './login-links.component.html',
  styleUrls: ['./login-links.component.css'],
})
export class LoginLinksComponent {
  constructor(private userapiService: UserapiService, private router: Router) {}
  profileUrl: any;
  firstName: any;
  lastName: any;
  sessionStatus = sessionStorage.getItem('userEmail') == null;

  ngOnInit() {
    this.getUserProfilePic();
  }
  ngOnChanges() {
    this.sessionStatus = sessionStorage.getItem('userEmail') == null;
  }

  logout() {
    sessionStorage.clear();
    this.sessionStatus = sessionStorage.getItem('userEmail') == null;
    this.router.navigate(['/home']);
  }

  async getUserProfilePic() {
    let userEmail = sessionStorage.getItem('userEmail') ?? '';
    const user = await this.userapiService.getAllUsers({
      email: userEmail,
    });
    this.profileUrl = user?.response[0]['photoUrl'];
    this.firstName = user?.response[0]['firstName'];
    this.lastName = user?.response[0]['lastName'];
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserapiService } from '../../service/user-api.service';

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
    this.getUserDetails();
  }
  ngOnChanges() {
    this.sessionStatus = sessionStorage.getItem('userEmail') == null;
  }

  logout() {
    sessionStorage.clear();
    this.sessionStatus = sessionStorage.getItem('userEmail') == null;
    this.router.navigate(['/home']);
  }

  async getUserDetails() {
    let userEmail = sessionStorage.getItem('userEmail') ?? '';
    const user = await this.userapiService.getAllUsers({
      email: userEmail,
    });
    this.profileUrl = user?.response[0]['photoUrl'];
    this.firstName = user?.response[0]['firstName'].toUpperCase();
    this.lastName = user?.response[0]['lastName'].toUpperCase();
  }
}

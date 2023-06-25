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
  userId: number = 0;
  sessionStatus = localStorage.getItem('userEmail') == null;

  ngOnInit() {
    this.getUserDetails();
  }

  ngOnChanges() {
    this.sessionStatus = localStorage.getItem('userEmail') == null;
  }

  logout() {
    localStorage.clear();
    this.sessionStatus = localStorage.getItem('userEmail') == null;
    this.router.navigate(['/home']).then(() => {
      window.location.reload();
    });
  }

  async getUserDetails() {
    let userEmail = localStorage.getItem('userEmail') ?? '';
    const user = await this.userapiService.getAllUsers({
      email: userEmail,
    });
    this.profileUrl = user?.response[0]['photoUrl'];
    this.firstName = user?.response[0]['firstName'].toUpperCase();
    this.lastName = user?.response[0]['lastName'].toUpperCase();
  }
}

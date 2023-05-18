import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'fl-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent {
  users:User[] = [];
  constructor(private httpClient: HttpClient) { }
  getAllCourse() {
    this.httpClient.get('assets/data/user.json').subscribe({
      next: (user) => {
        this.users = user as User[];
      },
      error: (errors) => { },
    })
  }
}

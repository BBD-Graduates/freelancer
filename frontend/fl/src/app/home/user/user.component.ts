import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course.model';

@Component({
  selector: 'fl-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  courses:Course[] = [];
  constructor(private httpClient: HttpClient) { }
  ngOnInit(): void {
    this.getAllCourse();
  }
  getAllCourse() {
    this.httpClient.get('assets/data/courses.json').subscribe({
      next: (course) => {
        this.courses = course as Course[];
      },
      error: (errors) => { },
    })
  }
}

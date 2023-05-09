import { Component, Input, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course.model';

@Component({
  selector: 'fl-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {
  @Input('course')
  course!:Course;

  ngOnInit(): void {
    
  }
  constructor(){}
}

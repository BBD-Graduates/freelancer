import { Component, Input } from '@angular/core';
import { Course } from 'src/app/model/course.model';

@Component({
  selector: 'fl-card-footer',
  templateUrl: './card-footer.component.html',
  styleUrls: ['./card-footer.component.scss']
})
export class CardFooterComponent {
  @Input('course')
  course!:Course;
rating = 4.5;
}

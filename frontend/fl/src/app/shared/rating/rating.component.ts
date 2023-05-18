import { Component, Input } from '@angular/core';

@Component({
  selector: 'fl-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent {
  @Input('value')
  value = 0;

  @Input('showNumber')
  showNumber = true;

  totalStars = 5;
}

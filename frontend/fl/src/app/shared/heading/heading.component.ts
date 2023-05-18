import { Component, Input } from '@angular/core';

@Component({
  selector: 'fl-heading',
  templateUrl: './heading.component.html',
  styleUrls: ['./heading.component.css']
})
export class HeadingComponent {
  @Input('subHeading')
  subHeading = '';

  @Input('headingText1')
  headingText1 = '';
  @Input('headingText2')
  headingText2 = '';
  @Input('headingText3')
  headingText3 = '';

  @Input('specialHeadingID')
  specialHeadingID:number|string = 2;

  @Input('align')
  align = 'C'; // C(center),L(left)
  constructor(){}
}

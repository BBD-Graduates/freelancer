import { Component, Input } from '@angular/core';

@Component({
  selector: 'fl-mobile',
  templateUrl: './mobile.component.html',
  styleUrls: ['./mobile.component.css']
})
export class MobileComponent {
  @Input('align')
  align = 'center';
}

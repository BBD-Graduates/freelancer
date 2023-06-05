import { Component, Input } from '@angular/core';

@Component({
  selector: 'fl-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent {
  @Input('align')
  align = 'center';
}

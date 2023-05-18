import { Component, Input } from '@angular/core';

@Component({
  selector: 'fl-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent {
  @Input('disabled')
  disabled = false;

  @Input('size')
  size = 'S'; // S,M,L

  @Input('shade')
  shade = 'NA'; // D,L,W,NA

  @Input('animation')
  animation='F'; // C,F

  @Input('name')
  name='';
}

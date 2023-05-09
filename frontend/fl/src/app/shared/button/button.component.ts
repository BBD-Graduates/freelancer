import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'fl-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {
  ngOnInit(): void {
  }
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

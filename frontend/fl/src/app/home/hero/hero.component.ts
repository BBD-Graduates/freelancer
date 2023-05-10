import { Component } from '@angular/core';

@Component({
  selector: 'fl-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.scss']
})
export class HeroComponent {
  canShowVideo = false;
  currentSize = 'S';
}

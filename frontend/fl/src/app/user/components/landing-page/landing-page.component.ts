import { Component } from '@angular/core';

@Component({
  selector: 'fl-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css'],
})
export class LandingPageComponent {
  canShowVideo = false;
  currentSize = 'S';
}

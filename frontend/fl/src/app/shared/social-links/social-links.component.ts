import { Component, Input } from '@angular/core';

@Component({
  selector: 'fl-social-links',
  templateUrl: './social-links.component.html',
  styleUrls: ['./social-links.component.css']
})
export class SocialLinksComponent {
  @Input('color')
  color = 'dynamic';

  @Input('align')
  align = 'center';
}

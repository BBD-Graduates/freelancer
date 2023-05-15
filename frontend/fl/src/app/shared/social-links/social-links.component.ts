import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'fl-social-links',
  templateUrl: './social-links.component.html',
  styleUrls: ['./social-links.component.scss']
})
export class SocialLinksComponent implements OnInit {
  @Input('color')
  color = 'dynamic';

  @Input('align')
  align = 'center';
  
  ngOnInit(): void {
  }

}

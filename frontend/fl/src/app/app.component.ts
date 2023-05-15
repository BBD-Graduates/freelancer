import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';

@Component({
  selector: 'fl-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {
  title = 'fl';
  constructor(private observer: BreakpointObserver) {
  }
  ngAfterViewInit(): void {
    
  }
}

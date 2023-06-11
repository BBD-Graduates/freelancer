import { Component } from '@angular/core';

@Component({
  selector: 'fl-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  isMenuScrolled = false;
  scrollCheck() {
    if (window.pageYOffset > 100) this.isMenuScrolled = true;
    else this.isMenuScrolled = false;
  }
  scrollToTop() {
    document.body.scrollIntoView({ behavior: 'smooth' });
  }
}

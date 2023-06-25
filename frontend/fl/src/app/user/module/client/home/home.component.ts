import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'client-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  selectedMenu: String = 'open';
  changeMenu(menu: String) {
    this.selectedMenu = menu;
  }
  constructor(private router: Router) {
  }
  isMenuActive(menu: string): boolean {
    console.log(this.router.url);
    return this.router.url.includes(menu);
  }
}

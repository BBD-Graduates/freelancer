import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'fl-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css'],
})
export class MenubarComponent {
  isMenuScrolled = false;
  isSidebarShowing = false;
  showAuthModel: boolean = false;
  constructor(private router: Router) {}

  @HostListener('window:scroll', ['$event'])
  scrollCheck() {
    if (window.pageYOffset > 100) this.isMenuScrolled = true;
    else this.isMenuScrolled = false;
  }
  openSidebar() {
    this.isSidebarShowing = true;
  }
  closeSidebar() {
    this.isSidebarShowing = false;
  }
  scrollToTop() {
    document.body.scrollIntoView({ behavior: 'smooth' });
  }
}

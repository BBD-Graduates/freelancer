import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'fl-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  isMenuScrolled = false;
  isSidebarShowing = false;
  @HostListener('window:scroll', ['$event'])
  scrollCheck() {
    if (window.pageYOffset > 100)
      this.isMenuScrolled = true;
    else
      this.isMenuScrolled = false;


    console.log(this.isMenuScrolled);
  }
  openSidebar(){  
    this.isSidebarShowing = true;
  }
  closeSidebar(){
    this.isSidebarShowing = false;
  }
  scrollToTop(){
    document.body.scrollIntoView({behavior:'smooth'}); 
  }
}

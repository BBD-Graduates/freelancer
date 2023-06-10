import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'fl-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css']
})
export class MenubarComponent {
  isMenuScrolled = false;
  isSidebarShowing = false;
  @HostListener('window:scroll', ['$event'])
  scrollCheck() {
    if (window.pageYOffset > 100)
      this.isMenuScrolled = true;
    else
      this.isMenuScrolled = false;
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

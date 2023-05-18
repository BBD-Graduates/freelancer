import { Component } from '@angular/core';

@Component({
  selector: 'fl-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  showSidebar = false;

  toggleSidebar() {
    this.showSidebar = !this.showSidebar;
  }
}

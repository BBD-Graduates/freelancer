import { Component } from '@angular/core';
import { UserapiService } from './../../user/service/user-api.service';
@Component({
  selector: 'fl-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent {
  constructor(private userapiService: UserapiService) {}
  showSidebar = false;

  toggleSidebar() {
    this.showSidebar = !this.showSidebar;
  }
}

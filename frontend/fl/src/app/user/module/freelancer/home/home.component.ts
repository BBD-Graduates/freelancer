import { Component } from '@angular/core';

@Component({
  selector: 'freelancer-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  selectedMenu:String = 'open';
  changeMenu(menu:String){
    this.selectedMenu=menu;
  }
}

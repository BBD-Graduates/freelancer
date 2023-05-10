import { AfterViewInit, Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { Course } from 'src/app/model/course.model';

@Component({
  selector: 'fl-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit,OnChanges {
  ngOnInit(): void {
  }

  @Input('courses')
  courses:Course[]=[];

  categories:String[] = [];

  // @ViewChild('slider')
  // slider:ElementRef;
  constructor(){}
  
  ngOnChanges(): void {
    // console.log(this.courses)
  }
  setUpSlider(){}
  getCategories(){
    this.categories = this.courses.map((course)=>{return course.category})
    let setUniqueCategories = [...new Set(this.categories)]
  this.categories = [...setUniqueCategories]
  }
}

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobLinksComponent } from './job-links.component';

describe('JobLinksComponent', () => {
  let component: JobLinksComponent;
  let fixture: ComponentFixture<JobLinksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobLinksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobLinksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

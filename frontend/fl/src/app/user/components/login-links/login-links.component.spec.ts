import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginLinksComponent } from './login-links.component';

describe('LoginLinksComponent', () => {
  let component: LoginLinksComponent;
  let fixture: ComponentFixture<LoginLinksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginLinksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginLinksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

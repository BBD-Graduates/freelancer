import { TestBed } from '@angular/core/testing';

import { AuthGuard2Service } from './auth-gaurd2.service';

describe('AuthGaurd2Service', () => {
  let service: AuthGuard2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuard2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

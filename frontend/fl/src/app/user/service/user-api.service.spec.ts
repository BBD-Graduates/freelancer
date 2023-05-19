import { TestBed } from '@angular/core/testing';

import { UserapiService } from './user-api.service';

describe('UserapiService', () => {
  let service: UserapiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserapiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

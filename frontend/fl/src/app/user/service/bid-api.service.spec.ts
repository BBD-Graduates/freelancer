import { TestBed } from '@angular/core/testing';

import { BidApiService } from './bid-api.service';

describe('BidApiService', () => {
  let service: BidApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BidApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

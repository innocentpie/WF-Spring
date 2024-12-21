import { TestBed } from '@angular/core/testing';

import { SzobaService } from './szoba.service';

describe('SzobaService', () => {
  let service: SzobaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SzobaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

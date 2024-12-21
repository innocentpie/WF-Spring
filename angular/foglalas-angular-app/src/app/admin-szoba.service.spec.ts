import { TestBed } from '@angular/core/testing';

import { AdminSzobaService } from './admin-szoba.service';

describe('AdminSzobaService', () => {
  let service: AdminSzobaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminSzobaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

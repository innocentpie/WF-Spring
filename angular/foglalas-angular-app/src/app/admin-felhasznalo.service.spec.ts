import { TestBed } from '@angular/core/testing';

import { AdminFelhasznaloService } from './admin-felhasznalo.service';

describe('AdminFelhasznaloService', () => {
  let service: AdminFelhasznaloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminFelhasznaloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

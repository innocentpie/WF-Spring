import { TestBed } from '@angular/core/testing';

import { AdminFoglalasService } from './admin-foglalas.service';

describe('AdminFoglalasService', () => {
  let service: AdminFoglalasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminFoglalasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SzobaListComponent } from './szoba-list.component';

describe('SzobaListComponent', () => {
  let component: SzobaListComponent;
  let fixture: ComponentFixture<SzobaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SzobaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SzobaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

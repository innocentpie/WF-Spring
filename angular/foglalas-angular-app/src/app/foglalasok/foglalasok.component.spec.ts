import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoglalasokComponent } from './foglalasok.component';

describe('FoglalasokComponent', () => {
  let component: FoglalasokComponent;
  let fixture: ComponentFixture<FoglalasokComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FoglalasokComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoglalasokComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

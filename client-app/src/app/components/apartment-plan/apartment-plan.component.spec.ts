import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApartmentPlanComponent } from './apartment-plan.component';

describe('ApartmentPlanComponent', () => {
  let component: ApartmentPlanComponent;
  let fixture: ComponentFixture<ApartmentPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApartmentPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApartmentPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

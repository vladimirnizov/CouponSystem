import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMyCouponsComponent } from './get-my-coupons.component';

describe('GetMyCouponsComponent', () => {
  let component: GetMyCouponsComponent;
  let fixture: ComponentFixture<GetMyCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetMyCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetMyCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

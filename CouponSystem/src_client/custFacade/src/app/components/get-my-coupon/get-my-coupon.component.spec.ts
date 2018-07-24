import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMyCouponComponent } from './get-my-coupon.component';

describe('GetMyCouponComponent', () => {
  let component: GetMyCouponComponent;
  let fixture: ComponentFixture<GetMyCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetMyCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetMyCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

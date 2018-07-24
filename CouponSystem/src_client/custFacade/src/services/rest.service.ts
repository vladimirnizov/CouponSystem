import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Coupon } from '../app/common/Coupon';

import { map, take } from 'rxjs/operators';




@Injectable({
  providedIn: 'root'
})
export class RestService {

  public sharedCoupon: Coupon;

  constructor(private _http: Http) { }


   // GET All Coupons
 public getAllCoupons() {

  const url = "/Customer/getAllCoupons";
  const promise = this._http.get(url).pipe(
  map(
    (couponResponse) => {
      return couponResponse.json();
    }
  ));
  return promise;
}


   // GET my Coupons
   public getMyCoupons() {

    const url = "/Customer/getPurchasedCoupons";
    const promise = this._http.get(url).pipe(
    map(
      (couponResponse) => {
        return couponResponse.json();
      }
    ));
    return promise;
  }

  public purchaseCoupon(coupon: Coupon){
    const url = "/Customer/purchaseCoupon/";
    const promise = this._http.post(url, coupon);
    return promise;

  }


 

  
}

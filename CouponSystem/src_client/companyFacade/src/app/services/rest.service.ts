import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Coupon } from '../common/Coupon';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RestService {

  public sharedCoupon: Coupon;

  constructor(private _http: Http) { }

  // POST create Coupon
  public createCoupon(coupon: Coupon) {

    const url = "/Company/createCoupon";
    const promise = this._http.post(url, coupon);
    return promise;

  }


 // GET All Coupons
 public getAllCoupons() {

  const url = "/Company/getAllCoupons";
  const promise = this._http.get(url).pipe(
  map(
    (couponResponse) => {
      return couponResponse.json();
    }
  ));
  return promise;
}

   // remove coupon
   public removeCoupon(id: number) {

    const url = "/Company/removeCoupon/" + id;
    const promise =this._http.delete(url);
    return promise;
  }

  public updateCoupon(coupon: Coupon){
    const url = "/Company/updateCoupon";
    const promise = this._http.put(url, coupon);
    return promise;
  }

}

import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon'
import { Router } from '@angular/router';



@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css']
})
export class GetAllCouponsComponent implements OnInit {

  public coupons: Coupon[];
  checked = false;
  date: Date;
  price: number;
  type: string;

  constructor(private _rest: RestService, private router : Router) {
    this.getCoupons();
   }



  ngOnInit() {
  }

 // Get all coupons
 getCoupons() {
  this.coupons = new Array;

  const self = this;
  this._rest.getAllCoupons().subscribe(
    (_coupons) => {
      if (self.coupons.length === 0) {
        for (let c of _coupons)
        {
          const date: Date = new Date;
          c = new Coupon('', date, date, 0, '', '', 0, '', c);
          c._startDate = new Date(c._startDate).toDateString();
          c._endDate = new Date(c._endDate).toDateString();
          
          
          if(this.type==c._type||this.type==null){
            if(this.price==null||c._price <= this.price)
            self.coupons.push(c);

          }
           
        }

        if(this.checked==false){
          this.price=null;
          this.type=null;
          }


      }
    }
  );
}

//  get coupon
getCoupon(c:Coupon){
  this._rest.sharedCoupon = c;
  this.router.navigateByUrl('getCoupon');
}



formatLabel(value: number | null) {
  if (!value) {
    return 0;
  }

  if (value >= 1000) {
    return Math.round(value / 1000) + 'k';
  }

  return value;
}

}
import { Component, OnInit } from '@angular/core';
import { RestService } from '../../../services/rest.service';
import { Coupon } from '../../common/Coupon'
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.scss']
})
export class PurchaseCouponComponent implements OnInit {
  
  private date: Date;
  public coupon: Coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '');
  

  constructor(private _rest: RestService, private router : Router) {
    this.purchaseCoupon();
   }
  ngOnInit() {
  }

  purchaseCoupon(){
    this.coupon=this._rest.sharedCoupon;
    this.coupon._startDate = new Date(this.coupon._startDate);
    this.coupon._endDate = new Date(this.coupon._endDate);

  swal({
    title: this.coupon._title,
    text: "Are you sure?",
    imageUrl: this.coupon._image,
    imageWidth: 400,
    imageHeight: 200,
    showCloseButton: true,
    confirmButtonText: 'Purchase',
    cancelButtonText: 'Cancel',
  }).then((result)=>{
    if(result.value){
 
      this._rest.purchaseCoupon(this._rest.sharedCoupon).subscribe(
        (response) => {
      swal({
       type: 'success',
       title: 'Purchased',
       showConfirmButton: false,
       timer: 2500
     }).then(()=>{
      setTimeout(()=>{this.router.navigateByUrl('getMyCoupons')}, 500);

     })
     
     
   },
   ()=>{
     swal({
       type: 'error',
       title: 'You already have this!',
       showConfirmButton: false,
       timer: 2500
     }).then(()=>{
      setTimeout(()=>{this.router.navigateByUrl('getAllCoupons')}, 500);

     })
   }
    
   )   
  }
else if(result.dismiss === swal.DismissReason.cancel){
  setTimeout(()=>{this.router.navigateByUrl('getAllCoupons')}, 500);
}

else
setTimeout(()=>{this.router.navigateByUrl('getAllCoupons')}, 500);

  
  })


  }

}

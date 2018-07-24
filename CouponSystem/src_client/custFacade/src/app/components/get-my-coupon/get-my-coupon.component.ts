import { Component, OnInit } from '@angular/core';
import { RestService } from '../../../services/rest.service';
import { Coupon } from '../../common/Coupon'
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-get-my-coupon',
  templateUrl: './get-my-coupon.component.html',
  styleUrls: ['./get-my-coupon.component.scss']
})
export class GetMyCouponComponent implements OnInit {

  public coupon: Coupon;

  constructor(private _rest: RestService, private router : Router) {
    this.getMyCoupon();
   }
  ngOnInit() {
  }

getMyCoupon(){
  this.coupon=this._rest.sharedCoupon;

  swal({
    title: this.coupon._title,
    html: 'ID: '+ this.coupon._id+ '<br />' + 'Type: '+ this.coupon._type+ '<br />'+'Amount: '
    + this.coupon._amount+ '<br />'+ 'Price: '+ this.coupon._price+ '<br />' + 'Message: '+ this.coupon._message+ '<br />'
    + 'Start date: '+ this.coupon._startDate+ '<br />' + 'End date: '+ this.coupon._endDate+ '<br />',
    imageUrl: this.coupon._image,
    imageWidth: 400,
    imageHeight: 200,
    showCloseButton: true,
   
    
  }).then(()=>{
  setTimeout(()=>{this.router.navigateByUrl('getMyCoupons')}, 500);
  })
}


}

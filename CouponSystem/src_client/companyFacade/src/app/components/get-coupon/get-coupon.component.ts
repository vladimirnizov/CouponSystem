import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon'
import { Router } from '@angular/router';
import swal from 'sweetalert2';


@Component({
  selector: 'app-get-coupon',
  templateUrl: './get-coupon.component.html',
  styleUrls: ['./get-coupon.component.css']
})
export class GetCouponComponent implements OnInit {

  public coupon: Coupon;

  constructor(private _rest: RestService, private router : Router) {
    this.getCoupon();
   }

  ngOnInit() {
  }

getCoupon(){
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
    showCancelButton: true,
    confirmButtonColor:  '#ff0000',
    cancelButtonColor: '#ffa500',
    confirmButtonText: 'Delete',
    cancelButtonText: 'Update',
  }).then((result)=>{
      
    if(result.value){
 
        this.removeCoupon(this.coupon);
     
    }
  else if(result.dismiss === swal.DismissReason.cancel){
    this.updateCoupon(this.coupon);

  }
  
  else
  setTimeout(()=>{this.router.navigateByUrl('getAll')}, 500);
  
    }
    
  
  )
}


  // remove coupon
  removeCoupon(coupon: Coupon){
    this._rest.sharedCoupon= coupon;
    this.router.navigateByUrl('removeCoupon');
  
   }
  
   //  update coupon
  updateCoupon(coupon: Coupon){
  
    this._rest.sharedCoupon= coupon;
    this.router.navigateByUrl('updateCoupon');
  }


}

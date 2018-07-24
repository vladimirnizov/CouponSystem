import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon'
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-remove-coupon',
  templateUrl: './remove-coupon.component.html',
  styleUrls: ['./remove-coupon.component.css']
})
export class RemoveCouponComponent implements OnInit {

  public coupon: Coupon;
  constructor(private _rest: RestService, private router : Router) { 
  }

  ngOnInit() {
    this.removeCoupon();

  }

  removeCoupon(){
    this.coupon=this._rest.sharedCoupon;


    swal({
      imageUrl: this.coupon._image,
      imageWidth: 400,
      imageHeight: 200,
      text: 'Are you sure?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor:  '#ff0000',
      cancelButtonColor: '#ffa500',
      confirmButtonText: 'Yes, delete it',
      cancelButtonText: 'Cancel',
    }).then((result)=>{
      if(result.value)
      {
         this._rest.removeCoupon(this._rest.sharedCoupon._id).subscribe(
           (response) => {
         swal({
          type: 'success',
          title: 'Removed',
          showConfirmButton: false,
          timer: 2500
        }).then(()=>this.router.navigateByUrl('getAll'));
        
        
      },
      ()=>{
        swal({
          type: 'error',
          title: 'error',
          showConfirmButton: false,
          timer: 2500
        }).then(()=>this.router.navigateByUrl('getAll'));
      }
       
      )
      }
      else
      this.router.navigateByUrl('getAll');
    })
  }

}

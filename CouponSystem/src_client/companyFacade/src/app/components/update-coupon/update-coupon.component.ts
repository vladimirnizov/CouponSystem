import { Component, OnInit } from '@angular/core';
import { RestService } from '../../services/rest.service';
import { Coupon } from '../../common/Coupon'
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css']
})
export class UpdateCouponComponent implements OnInit {

  private date: Date;
  public coupon: Coupon = new Coupon('', this.date, this.date, 0, '', '', 0, '');

  public title: string;
  public startDate: Date;
  public endDate: Date;
  public type: string;
  public amount: number;
  public message: string;
  public price: number;
  public image: string;




  constructor(private _rest: RestService, private router : Router) { }

  ngOnInit() {
  }

  updateCoupon(){

    this.coupon=this._rest.sharedCoupon;
    this.coupon._startDate = new Date(this.coupon._startDate);
    this.coupon._endDate = new Date(this.coupon._endDate);

       if(this.message!=null){
        this.coupon._message=this.message;
    }
    if(this.endDate!=null){
      this.coupon._endDate=this.endDate;
  }
  if(this.price!=null){
    this.coupon._price=this.price;
}


if(this.image!=null){
  this.coupon._image=this.image;
}


    const self = this;
    this._rest.updateCoupon(this.coupon).subscribe(
      (response) => {
          ('Coupon: ' + this.coupon._title , ' was updated successfully' , 'success');


          swal({
            type: 'success',
            title: this.coupon._title +' updated',
            showConfirmButton: false,
            timer: 3000
          }).then(()=> {
            setTimeout(()=>{ this.router.navigateByUrl('getAll')});
          })
      },
      () => {
          ('Coupon:' + this.coupon._title , 'not updated', 'error');
          swal({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            timer: 3000

           
          }).then(()=>{
            setTimeout(()=>{ this.router.navigateByUrl('getAll')});

          })
      
        }
    );

    this.clearMembers();
  }
  clearMembers() {
    this.title = null;
    this.startDate = null;
    this.endDate = null;
    this.amount = null;
    this.type = null;
    this.message = null;
    this.price = null;
    this.image = null;
  }

}

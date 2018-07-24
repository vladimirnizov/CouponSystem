import { Component, OnInit } from '@angular/core';
import { Customer } from '../../common/Customer';
import { RestService } from '../../service/rest.service';
import { Router } from '@angular/router';

import swal from 'sweetalert2';

@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css']
})
export class GetCustomerComponent implements OnInit {

  public customer: Customer;
  constructor(private _rest: RestService, private router : Router) { }

  ngOnInit() {
    this.getCustomer();
  }

  // remove customer
removeCustomer(id:number){
  this._rest.shared = id;
  this.router.navigateByUrl('removeCustomer');

 }

 //  update customer
updateCustomer(c:Customer){

  this._rest.sharedCust=c;
  this.router.navigateByUrl('updateCustomer');
}
  
  getCustomer(){
    this.customer=this._rest.sharedCust;
    swal({
      title: this.customer._customertName,
      html: 'ID: '+ this.customer._id+ '<br />' + 'Password: '+ this.customer._pass+ '<br />',
      confirmButtonColor:  '#ff0000',
      cancelButtonColor: '#ffa500',
      showCloseButton: true,
      showCancelButton: true,
      confirmButtonText: 'Delete',
      cancelButtonText: 'Update',
    }).then((result)=>{
      
      if(result.value){
        swal({
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
             this._rest.removeCustomer(this.customer._id).subscribe(
               (response) => {
             swal({
              type: 'success',
              title: 'Removed',
              showConfirmButton: false,
              timer: 2500
            }).then(()=>{setTimeout(()=>{window.location.href="#Customers"}, 50)});
            
            
          },
          ()=>{
            swal({
              type: 'error',
              title: 'error',
              showConfirmButton: false,
              timer: 2500
            })
          }
           
          )
          }
          else
          setTimeout(()=>{window.location.href="#Customers"}, 50);
        })
        
      }
    else if(result.dismiss === swal.DismissReason.cancel){
      this.updateCustomer(this.customer);

    }
    
    else
    setTimeout(()=>{this.router.navigateByUrl('')}, 500);
    
      }
      
    
    )
    
    }
}

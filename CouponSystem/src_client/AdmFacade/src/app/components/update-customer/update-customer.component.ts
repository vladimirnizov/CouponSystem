import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Customer } from '../../common/Customer';
import swal from 'sweetalert2';



@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  public customer: Customer;

  public id: number;

  public password: string;
  constructor(private _rest: RestService) { }

  ngOnInit() {
    this.updateCustomer();
  }

  updateCustomer() {

    swal({
      title: 'New password:',
      input: 'text',
      inputPlaceholder: 'Enter new password here',
      showCancelButton: true,
      cancelButtonColor: '#ff0000',
      inputValidator: (value) => {
        return !value && 'You need to write something!'
      }
    }).then((pass) => {
      if(pass.value){
      this.password=pass.value;

    
    this.customer = new Customer(this._rest.sharedCust._customertName, this.password);
    this.customer._id=this._rest.sharedCust._id;
     const self = this;
     this._rest.updateCustomer(this.customer).subscribe(
       (response) => {
           ('Company:' + self.customer._customertName, ' was updated successfully' , 'success');
       
           swal({
            type: 'success',
            title: this.customer._customertName+' has been updated',
            showConfirmButton: false,
            timer: 3000
          }).then(()=> {
            setTimeout(()=>{ window.location.href="#Customers", 500});
          })
       
          },
       () => {
           ('Company:' + self.customer._customertName , ' was not updated', 'error');
       
           swal({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
           
          }).then(()=>{
            setTimeout(()=>{ window.location.href="#Customers", 500});

          })
       
          }
     );
 
     
     this.password = null;

     
 
    }
    else{
      setTimeout(()=>{ window.location.href="#Customers", 500});

    }
    })
   }

}
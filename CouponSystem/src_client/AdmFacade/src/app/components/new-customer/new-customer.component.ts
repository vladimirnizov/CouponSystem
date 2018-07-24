import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Customer } from '../../common/Customer';
import swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit {

  public customer: Customer;

  public name: string;
  public password: string;


  constructor(private _rest: RestService, private router: Router) { }

  ngOnInit() {
    this.createCustomer();
  }



  createCustomer() {

    swal({
      title: 'Step 1',
      input: 'text',
      text: 'Give a name to new customer:',
      inputPlaceholder: 'Enter name here',
      showCancelButton: true,
      inputValidator: (value) => {
        return !value && 'You need to write something!'
      }
    }).then((name)=>{
      if(name.value){
        this.name = name.value;

        swal({
          title: 'Step 2',
          input: 'text',
          text: 'Set a password to new customer:',
          inputPlaceholder: 'Enter password here',
          showCancelButton: true,
          inputValidator: (value) => {
            return !value && 'You need to write something!'
          }
        }).then((pass)=>{

          if(pass.value){
            this.password=pass.value;

            this.customer = new Customer(this.name, this.password);

            const self = this;
            this._rest.createCustomer(this.customer).subscribe(
              (response) => {
                  ('Company:' + self.customer._customertName , ' was created successfully' , 'success');

                  swal({
                    type: 'success',
                    title: this.customer._customertName+' has been saved',
                    showConfirmButton: false,
                    timer: 3000
                  }).then(()=> {
                    setTimeout(()=>{ window.location.href="#Customers", 500});
                  })

              },
              () => {
                  ('Company:' + self.customer._customertName , ' was not created, maybe name already exist.', 'error');

                  swal({
                    type: 'error',
                    title: 'Oops...',
                    text: 'Something went wrong!',
                   
                  }).then(()=>{
                    setTimeout(()=>{ window.location.href="#Customers", 500});
        
                  })
              }
            );
        
            this.name = null;
            this.password = null;


          }
          else if(pass.dismiss=== swal.DismissReason.cancel){
            setTimeout(()=>{this.router.navigateByUrl('')}, 500); 
          }
          else{
            setTimeout(()=>{this.router.navigateByUrl('')}, 500); 
    
          }

        })

      }
      else if(name.dismiss=== swal.DismissReason.cancel){
        setTimeout(()=>{this.router.navigateByUrl('')}, 500); 
      }
      else{
        setTimeout(()=>{this.router.navigateByUrl('')}, 500); 

      }

    })





  }

}

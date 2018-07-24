import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Customer } from '../../common/Customer';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-customers',
  templateUrl: './get-customers.component.html',
  styleUrls: ['./get-customers.component.css']
})
export class GetCustomersComponent implements OnInit {

  public customers: Customer[];

  constructor(private _rest: RestService, private router : Router) {
    this.getCustomers();
   }

  ngOnInit() {
  }

 // Shows all customers
 getCustomers() {
  this.customers = new Array;

   const self = this;
   this._rest.getAllCustomers().subscribe(
     (_customers) => {
       if (self.customers.length === 0) {

         for (let c of _customers)
         {
           c = new Customer('', '', c);
           self.customers.push(c);
         }
       }
     }
   );
 }



//  get customer
getCustomer(c:Customer){
  this._rest.sharedCust = c;
  this.router.navigateByUrl('getCustomer');
}

}
import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-remove-customer',
  templateUrl: './remove-customer.component.html',
  styleUrls: ['./remove-customer.component.css']
})
export class RemoveCustomerComponent implements OnInit {

  constructor(private _rest: RestService, private router : Router) { }


    // remove customer
    removeCustomer(){
      const self = this;
      this._rest.removeCustomer(this._rest.shared).subscribe()
   
  
      setTimeout(()=>{this.router.navigateByUrl('getCustomers')}, 500);
     }

  ngOnInit() {
  }

}

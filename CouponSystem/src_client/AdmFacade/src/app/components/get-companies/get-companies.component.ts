import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Company } from '../../common/Company';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-companies',
  templateUrl: './get-companies.component.html',
  styleUrls: ['./get-companies.component.css']
})
export class GetCompaniesComponent implements OnInit {

  public companies: Company[];

  constructor(private _rest: RestService, private router : Router) {
    this.getCompanies();
   }

  ngOnInit() {
  }

share(x:number){
  this._rest.shared=x;
}

  
 // Shows all companies
 getCompanies() {
  this.companies = new Array;

   const self = this;
   this._rest.getAllCompanies().subscribe(
     (_companies) => {
       if (self.companies.length === 0) {

         for (let c of _companies)
         {
           c = new Company('', '', '', c);
           self.companies.push(c);
         }
       }
     }
   );
 }

 

//  get company
getCompany(c:Company){
  this._rest.sharedComp = c;
  this.router.navigateByUrl('getCompany');
}

//  update company
updateCompany(c:Company){
  // this._rest.shared = id;
  // this._rest.sharedString=name;
  this._rest.sharedComp=c;
  this.router.navigateByUrl('updateCompany');
}

}

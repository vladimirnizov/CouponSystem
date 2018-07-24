import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'
import { Company } from '../common/Company';
import { Customer } from '../common/Customer';

@Injectable()
export class RestService {

  public sharedComp: Company;
  public sharedCust: Customer;
  public shared: number;
  public sharedString: string;
  constructor(private _http: Http) { }


  // GET all companies
public getAllCompanies() {

  const url = "/Admin/getallcompanies";
  return this._http.get(url).map(
    function (companiesResponse)
    {
      return companiesResponse.json();
    }
  );
}


  // GET all customers
  public getAllCustomers() {

    const url = "/Admin/getallcustomers";
    return this._http.get(url).map(
      function (customersResponse)
      {
        return customersResponse.json();
      }
    );
  }

    // GET company
    public getCompany(id: number) {

      const url = "/Admin/getbyid/" + id;
      const promise =this._http.get(url).map(
        function (companyResponse)
        {
          return companyResponse.json();
        }
      );
      return promise;
    }

    // POST create new company
public createCompany(company: Company) {

  const url = "/Admin/createCompany";
  const promise = this._http.post(url, company);
  return promise;
}

    // remove company
    public removeCompany(id: number) {

      const url = "/Admin/removeCompany/" + id;
      const promise =this._http.delete(url);
      return promise;
    }

        // PUT update company
public updateCompany(company: Company) {

  const url = "/Admin/updateCompany";
  const promise = this._http.put(url, company);
  return promise;
}

   // remove customer
   public removeCustomer(id: number) {

    const url = "/Admin/removeCustomer/" + id;
    const promise =this._http.delete(url);

    return promise;
  }

      // POST create new customer
public createCustomer(customer: Customer) {

  const url = "/Admin/createCustomer";
  const promise = this._http.post(url, customer);
  return promise;
}

        // PUT update customer
        public updateCustomer(customer: Customer) {

          const url = "/Admin/updateCustomer";
          const promise = this._http.put(url, customer);
          return promise;
        }


            // GET customer
    public getCustomer(id: number) {

      const url = "/Admin/getCustomer/" + id;
      const promise =this._http.get(url).map(
        function (customerResponse)
        {
          return customerResponse.json();
        }
      );
      return promise;
    }
}


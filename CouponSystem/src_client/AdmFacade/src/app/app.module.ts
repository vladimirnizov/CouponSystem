import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RestService } from '../app/service/rest.service';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { GetCompaniesComponent } from './components/get-companies/get-companies.component';
import { GetCompanyComponent } from './components/get-company/get-company.component';
import { GetCustomerComponent } from './components/get-customer/get-customer.component';
import { GetCustomersComponent } from './components/get-customers/get-customers.component';
import { NewCompanyComponent } from './components/new-company/new-company.component';
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { RemoveCustomerComponent } from './components/remove-customer/remove-customer.component';
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
import { LogoutComponent } from './components/logout/logout.component';


@NgModule({
  declarations: [
    AppComponent,
    GetCompaniesComponent,
    GetCompanyComponent,
    GetCustomerComponent,
    GetCustomersComponent,
    NewCompanyComponent,
    NewCustomerComponent,
    RemoveCustomerComponent,
    UpdateCompanyComponent,
    UpdateCustomerComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path:'getCustomer' ,
        component: GetCustomerComponent
      },
      {
        path:'logout' ,
        component: LogoutComponent
      },
      {
        path:'newCustomer' ,
        component: NewCustomerComponent
      },
      {
        path:'removeCustomer' ,
        component: RemoveCustomerComponent
      },
      {
        path:'updateCustomer' ,
        component: UpdateCustomerComponent
      },
        {
       path:'getCompanies' ,
       component: GetCompaniesComponent
     },
     {
       path:'getCustomers' ,
       component: GetCustomersComponent
     },
     {
      path:'newCompany' ,
      component: NewCompanyComponent
    },

    {
      path:'updateCompany' ,
      component: UpdateCompanyComponent
    },
     {
      path:'getCompany' ,
      component: GetCompanyComponent
}
])
],
providers: [RestService],
bootstrap: [AppComponent]
})
export class AppModule { }

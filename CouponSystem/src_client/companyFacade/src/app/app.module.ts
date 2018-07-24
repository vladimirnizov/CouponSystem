import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatToolbarModule, MatIconModule, MatCardModule } from '@angular/material';
import {MatTableModule} from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RestService } from '../app/services/rest.service';
import { HttpModule } from '@angular/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSliderModule} from '@angular/material/slider';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';


import { AppComponent } from './app.component';
import { CreateCouponComponent } from './components/create-coupon/create-coupon.component';
import { GetAllCouponsComponent } from './components/get-all-coupons/get-all-coupons.component';
import { GetCouponComponent } from './components/get-coupon/get-coupon.component';
import { RemoveCouponComponent } from './components/remove-coupon/remove-coupon.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';
import { LogoutComponent } from './components/logout/logout.component';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    CreateCouponComponent,
    GetAllCouponsComponent,
    GetCouponComponent,
    RemoveCouponComponent,
    UpdateCouponComponent,
    LogoutComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatCheckboxModule,
    MatIconModule,
    MatCardModule,
    MatTableModule,
    MatSliderModule,
    MatDatepickerModule, 
    MatSelectModule,
    MatInputModule,
    RouterModule.forRoot([
      {
        path:'newCoupon' ,
        component: CreateCouponComponent
      },
      {
        path:'removeCoupon' ,
        component: RemoveCouponComponent
      },
      {
        path:'updateCoupon' ,
        component: UpdateCouponComponent
      },
      {
        path:'getCoupon' ,
        component: GetCouponComponent
      },
      {
        path:'logout' ,
        component: LogoutComponent
      },
      {
        path:'Home' ,
        component: HomeComponent
      },
      {
        path:'getAll' ,
        component: GetAllCouponsComponent
      }
    ])
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule { }

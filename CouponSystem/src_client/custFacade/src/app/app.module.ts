import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatToolbarModule, MatIconModule, MatCardModule } from '@angular/material';
import {MatTableModule} from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RestService } from '../services/rest.service';
import { HttpModule } from '@angular/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSliderModule} from '@angular/material/slider';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';



import { AppComponent } from './app.component';
import { GetAllComponent } from './compomemts/get-all/get-all.component';
import { GetMyCouponsComponent } from './components/get-my-coupons/get-my-coupons.component';
import { GetCouponComponent } from './cpmponents/get-coupon/get-coupon.component';
import { GetMyCouponComponent } from './components/get-my-coupon/get-my-coupon.component';
import { PurchaseCouponComponent } from './components/purchase-coupon/purchase-coupon.component';
import { HomeComponent } from './components/home/home.component';
import { LogoutComponent } from './components/logout/logout.component';


@NgModule({
  declarations: [
    AppComponent,
    GetAllComponent,
    GetMyCouponsComponent,
    GetCouponComponent,
    GetMyCouponComponent,
    PurchaseCouponComponent,
    HomeComponent,
    LogoutComponent
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
        path:'getAllCoupons' ,
        component: GetAllComponent
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
        path:'purchaseCoupon' ,
        component: PurchaseCouponComponent
      },
      {
        path:'getMycoup' ,
        component: GetMyCouponComponent
      },
      {
        path:'getCoupon' ,
        component: GetCouponComponent
      },
      {
        path:'getMyCoupons' ,
        component: GetMyCouponsComponent
      }
    ])
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule { }

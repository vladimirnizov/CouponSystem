import { Coupon } from './Coupon';
/**
 * Class that declaire customers
 */
export class Customer {
  // CTR
  constructor(private customerName: string ,
     private pass: string , private customer?: Customer, private coupons ?: Coupon[] , private id?: number) {

      if (this.customer != null) {

        this.id = customer.id;
        this.customerName = customer.customerName;
        this.pass = customer.pass;
        this.coupons = customer.coupons;
      }
     }

  // Getters and Setters
  get _id(): number
  {
  return this.id;
  }

  set _id(newNumber: number)
  {
  this.id = newNumber;
  }

  get _customertName(): string
  {
    return this.customerName;
  }

  set _customertName(newName: string)
  {
    this.customerName = newName;
  }

  get _pass(): string
  {
    return this.pass;
  }

  set _pass(newPassword: string)
  {
    this.pass = newPassword;
  }

  get _coupons(): Coupon[]
  {
    return this.coupons;
  }

  set _coupons(newCoupons: Coupon[])
  {
    this.coupons = newCoupons;
  }

  }
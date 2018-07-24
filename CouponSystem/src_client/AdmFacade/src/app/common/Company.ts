import { Coupon } from './Coupon';
/**
 * Class that declaire companies
 */
export class Company {
// CTR
constructor(private companyName: string , private pass: string ,
  private email: string , private company?: Company, private coupons?: Coupon[] , private id?: number ) {

    if (this.company != null) {

      this.id = company.id;
      this.companyName = company.companyName;
      this.pass = company.pass;
      this.email = company.email;
      this.coupons = company.coupons;
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

get _compName(): string
{
  return this.companyName;
}

set _compName(newName: string)
{
  this.companyName = newName;
}

get _password(): string
{
  return this.pass;
}

set _password(newPassword: string)
{
  this.pass = newPassword;
}

get _email(): string
{
  return this.email;
}

set _email(newEmail: string)
{
  this.email = newEmail;
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
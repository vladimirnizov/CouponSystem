import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Company } from '../../common/Company';
import swal from 'sweetalert2';
import { Router } from '@angular/router';


@Component({
  selector: 'app-new-company',
  templateUrl: './new-company.component.html',
  styleUrls: ['./new-company.component.css']
})
export class NewCompanyComponent implements OnInit {

  public company: Company;

  public name: string;
  public password: string;
  public email: string;



  constructor(private _rest: RestService, private router: Router) { }

  ngOnInit() {
    this.createCompany();
  }

  createCompany() {


    swal({
      title: 'Step 1',
      input: 'text',
      text: 'Give a name to new company:',
      inputPlaceholder: 'Enter name here',
      showCancelButton: true,
      inputValidator: (value) => {
        return !value && 'You need to write something!'
      }
    }).then((name) => {
      if(name.value){
      this.name = name.value;

      swal({
        title: 'Step 2',
        input: 'text',
        text: 'Give a password to new company:',
        inputPlaceholder: 'Enter password here',
        showCancelButton: true,
        inputValidator: (value) => {
          return !value && 'You need to write something!'
        }
      }).then((pass) => {

        if(pass.value){
        this.password = pass.value;

        swal({
          title: 'Step 3',
          input: 'text',
          text: 'Give a email of new company:',
          inputPlaceholder: 'Enter email here',
          showCancelButton: true,
          inputValidator: (value) => {
            return !value && 'You need to write something!'
          }
        }).then((mail) => {

          if(mail.value){
          this.email=mail.value;

    this.company = new Company(this.name, this.password, this.email);

    const self = this;
    this._rest.createCompany(this.company).subscribe(
      (response) => {
          ('Company:' + self.company._compName , ' was created successfully' , 'success');

          swal({
            type: 'success',
            title: this.company._compName+' has been saved',
            showConfirmButton: false,
            timer: 3000
          }).then(()=> {
            setTimeout(()=>{ window.location.href="#Companies", 500});
          })
      },
      () => {
          ('Company:' + self.company._compName , ' was not created, maybe name already exist.', 'error');

          swal({
            type: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
           
          }).then(()=>{
            setTimeout(()=>{ window.location.href="#Companies", 500});

          })
      }
    );

    this.name = null;
    this.password = null;
    this.email = null;
    
  
  


  }
  else if(mail.dismiss=== swal.DismissReason.cancel){
    setTimeout(()=>{this.router.navigateByUrl('')}, 500);  }
  
  else{
    setTimeout(()=>{this.router.navigateByUrl('')}, 500);  }


  })  
}
else if(pass.dismiss=== swal.DismissReason.cancel){
  setTimeout(()=>{this.router.navigateByUrl('')}, 500);}

else{
  setTimeout(()=>{this.router.navigateByUrl('')}, 500);}

})


}

else if(name.dismiss=== swal.DismissReason.cancel){
  setTimeout(()=>{this.router.navigateByUrl('')}, 500);}

else{
  setTimeout(()=>{this.router.navigateByUrl('')}, 500);}

})

}
  

}

import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Company } from '../../common/Company';
import swal from 'sweetalert2';


@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {


  public company: Company;

  public id: number;
  public name: string;
  public password: string;
  public email: string;

  constructor(private _rest: RestService) { }

  ngOnInit() {
    this.updateCompany();
  }

  updateCompany() {


    swal({
      title: 'New password:',
      input: 'text',
      inputPlaceholder: 'Enter new password here',
      showCancelButton: true,
      cancelButtonColor: '#ff0000',

      inputValidator: (value) => {
        return !value && 'You need to write something!'
      }
    }).then((pass) => {

      if(pass.value){
      this.password=pass.value;
   
      swal({
        title: 'New email:',
        input: 'text',
        inputPlaceholder: 'Enter new email here',
        showCancelButton: true,
        cancelButtonColor: '#ff0000',

        inputValidator: (value) => {
          return !value && 'You need to write something!'
        }
      }).then((email) =>{

        if(email.value){
        this.email = email.value;

    
   this.company = new Company(this._rest.sharedComp._compName, this.password, this.email);
   this.company._id=this._rest.sharedComp._id;
    const self = this;
    this._rest.updateCompany(this.company).subscribe(
      (response) => {
          ('Company:' + self.company._compName , ' was updated successfully' , 'success');


          swal({
            type: 'success',
            title: this.company._compName+' has been updated',
            showConfirmButton: false,
            timer: 3000
          }).then(()=> {
            setTimeout(()=>{ window.location.href="#Companies", 500});
          })
      },
      () => {
          ('Company:' + self.company._compName , ' was not updated', 'error');
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
  else{
    setTimeout(()=>{ window.location.href="#Companies", 500});

  }
  })
}
else{
  setTimeout(()=>{ window.location.href="#Companies", 500});
}
    })

  }


}


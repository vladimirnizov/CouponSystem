import { Component, OnInit } from '@angular/core';
import { RestService } from '../../service/rest.service';
import { Company } from '../../common/Company';
import { Router } from '@angular/router';



import swal from 'sweetalert2';


@Component({
  selector: 'app-get-company',
  templateUrl: './get-company.component.html',
  styleUrls: ['./get-company.component.css']
})
export class GetCompanyComponent implements OnInit {

  public company: Company;

  constructor(private _rest: RestService, private router : Router) { }


  ngOnInit() {

    this.getCompany();
  }


  //  update company
updateCompany(c:Company){

  this._rest.sharedComp=c;
  this.router.navigateByUrl('updateCompany');
}

getCompany(){
  this.company=this._rest.sharedComp;

swal({
  title: this.company._compName,
  html: 'ID: '+ this.company._id+ '<br />' + 'Password: '+ this.company._password+ '<br />' + 'Mail: ' + this.company._email + '<br />',
  showCloseButton: true,
  showCancelButton: true,
  confirmButtonColor:  '#ff0000',
  cancelButtonColor: '#ffa500',
  confirmButtonText: 'Delete',
  cancelButtonText: 'Update',
}).then((result)=>{
  
  if(result.value){
    swal({
      text: 'Are you sure?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor:  '#ff0000',
      cancelButtonColor: '#ffa500',
      confirmButtonText: 'Yes, delete it',
      cancelButtonText: 'Cancel',
    }).then((result)=>{
      if(result.value)
      {
         this._rest.removeCompany(this.company._id).subscribe(
           (response) => {
         swal({
          type: 'success',
          title: 'Removed',
          showConfirmButton: false,
          timer: 2500
        }).then(()=>{setTimeout(()=>{window.location.href="#Companies"}, 50)});
        
        
      },
      ()=>{
        swal({
          type: 'error',
          title: 'error',
          showConfirmButton: false,
          timer: 2500
        })
      }
       
      )
      }
      else
      setTimeout(()=>{window.location.href="#Companies"}, 50);
    })
  }
else if(result.dismiss === swal.DismissReason.cancel){
  this.updateCompany(this.company);

}

else
setTimeout(()=>{this.router.navigateByUrl('')}, 500);

  }
  

)

}

}

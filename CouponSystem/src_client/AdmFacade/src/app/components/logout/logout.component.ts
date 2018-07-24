import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { RestService } from '../../service/rest.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private _rest: RestService, private router : Router) { 
    this.logout();
  }

  ngOnInit() {
  }

logout(){
  swal({
    title: "Exit? Are you sure?",
  
    showCancelButton: true,
    confirmButtonText: 'Yes!',
}).then((result)=>{
  if(result.value){

    
    setTimeout(()=>{window.location.href='/index.html'}, 500); 
}
else
setTimeout(()=>{this.router.navigateByUrl('#top')}, 500);
})

}
}
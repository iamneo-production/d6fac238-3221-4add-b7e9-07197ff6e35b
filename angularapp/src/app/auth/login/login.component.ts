import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entity/user';
import { UserService } from '../services/user.service';
declare let alertify:any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  log:LoginComponent;
   user=new  User();
  userlocal:any;
  local:any;
  constructor(private service:UserService,private router:Router) { }

  ngOnInit(): void {
    }
  login(){
    this.service.loginUser(this.user).subscribe(
      data=>{
        console.log("received");
        localStorage.setItem("response",JSON.stringify(data));
        this.userlocal=JSON.parse(localStorage.getItem("response"));
        localStorage.setItem("userdata",JSON.stringify(this.userlocal.data));
        localStorage.setItem("token",JSON.stringify(this.userlocal.token));
        this.local=this.userlocal.data;
        console.log(localStorage.getItem("token"));
        
        if(this.local.role==="User"){
          this.router.navigate(['home'])
          alertify.success('Welcome User');
        }
        if(this.local.role==="Admin"){
          alertify.success('Welcome Admin');
          this.router.navigate(['admin'])
        }
        
        
      },
      error=>{console.log("not received")
      alertify.error('Login Unsuccessful')
    }

      
     
    );
  }
  
}

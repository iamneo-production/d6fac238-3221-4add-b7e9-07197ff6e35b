import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

import { User } from '../../entity/user';

declare let alertify:any;


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user=new User();
  constructor(private service:UserService,private router:Router) { }

  ngOnInit(): void {
  }
  signup(){
    this.service.signupUser(this.user).subscribe(
      data=>{
        console.log("Registered");
        alertify.success("Registration Successful");
        this.router.navigate([''])
      },
      error=>{
        console.log("Not registered")
        alertify.error("Registration UnSuccessful");
      }
    );
  }


}

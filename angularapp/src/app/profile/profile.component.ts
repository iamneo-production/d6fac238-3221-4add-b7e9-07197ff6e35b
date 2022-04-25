import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entity/user';
import { UserService } from '../services/user.service';
declare let alertify:any;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user=new User();
  id:number;
  constructor(private userService:UserService,private router:Router) { }
  temp:any;
  ngOnInit(): void {
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    this.id=this.temp.id
    this.userService.getUser(this.id).subscribe(
      data=>{
        this.user=data;
      },
      error=>{
        console.log(error);
      }
    );
    console.log(this.user);
  }
  back(){
    this.router.navigate(['home']);
  }
  onSubmitUser(){
    this.userService.editUser(this.user, this.id)
    .subscribe(data => {
      console.log(data);
      this.user = new User();
      alertify.success("User Updated");
      this.ngOnInit();

    }, error => {console.log(error)
      alertify.error("User not Updated");
    }
    );
  }


}

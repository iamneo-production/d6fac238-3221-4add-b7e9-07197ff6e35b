import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entity/user';
import { UserService } from '../services/user.service';
declare let alertify:any;

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {

  id:any;
  public page=1;
  public pageSize=5;
  users:Array<User>;
  user=new User();
  username:any;
  user1= new User();
  constructor(private userService:UserService,private router:Router) { }

  ngOnInit(): void {
    this.listUsers();
  }
 
  listUsers(){
    this.userService.listallUsers().subscribe(
      data=>{
        console.log(data)
        this.users=data;
      },
      error=>console.log(error)
    );
  }
  newUser(){
    this.userService.signupUser(this.user).subscribe(
      data=>{
        console.log("Done");
        alertify.success("Registration Successful");
        setTimeout(function(){
          location.reload();},800);
      },
      error=>{
        console.log("Not Done")
        alertify.error("Registration UnSuccessful");
      }
    )
  }
  edituser(id:number){
    this.id=id;
    this.userService.getUser(id)
      .subscribe(data => {
        console.log(data)
        this.user1 = data;
      }, error => console.log(error));
  }
  
  onSubmitUser(){
    this.userService.editUser(this.user1, this.id)
    .subscribe(data => {
      console.log(data);
      this.user1 = new User();
      alertify.success("User Updated");
      this.reloadData();

    }, error => {console.log(error)
      alertify.error("User not Updated");
    }
    );
  }
  reloadData(){
    setTimeout(function(){
      location.reload();},800);
  }
  
  getUserId(id:number){
    this.id=id;
    this.userService.getUser(id)
      .subscribe(data => {
        console.log(data)
        this.user1 = data;
      }, error => console.log(error));
  }
  deleteUser(){
    this.userService.deleteUserId(this.id).subscribe(
      data=>{
        console.log('deleted');
        alertify.success("User deleted");
        this.reloadData();
      },
      error=> {console.log(error);
        alertify.error("User not Deleted");
      }
    )
  }
}
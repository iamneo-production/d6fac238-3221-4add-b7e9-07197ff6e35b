import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { User } from '../entity/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  local:any;
 
  constructor(private http:HttpClient) { }
  public loginUser(user:User):Observable<any>{
    return this.http.post(apiUrl+"login",user)
  }
  public signupUser(user:User):Observable<any>{
    return this.http.post(apiUrl +"signup",user)
  }
  public editUser(user:User,id:number):Observable<any>{
    return this.http.post(apiUrl+"edituser/"+id,user)
  }
  public listallUsers():Observable<any>{
    return this.http.get(apiUrl+"alluser");
  }
  public getUser(id:number):Observable<any>{
    return this.http.get(apiUrl+"getuser/"+id);
  }
  public deleteUserId(id:number):Observable<any>{
    return this.http.get(apiUrl+"deleteuser/"+id);
  }
  public isIoggedIn():boolean{
    if(localStorage.getItem("token")===null){
      return false
    }
    return true
  }
  public isAdmin():boolean{
    this.local=JSON.parse(localStorage.getItem("userdata"));
    console.log(this.local);
    if(this.local.role!="Admin"){
      return false;
    }
    return true;
  }
}

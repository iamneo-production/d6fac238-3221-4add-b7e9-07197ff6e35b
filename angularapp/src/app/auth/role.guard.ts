import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
declare let alertify:any;
@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private userservice:UserService,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    var isauthenticatedAdmin=this.userservice.isAdmin();
    if(!isauthenticatedAdmin){
      alertify.alert('User Not Authorized', 'Not Permitted to vist this page');
      this.router.navigate(['home'])
    } 
    return true;
    }
}

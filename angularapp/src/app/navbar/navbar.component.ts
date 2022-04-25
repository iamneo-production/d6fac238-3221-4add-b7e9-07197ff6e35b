import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  checkuser:any;
  temp:any;
  username:String;
  public totalItem : number ;
  
  constructor(private router:Router,private cartService:CartService) { }

  ngOnInit(): void {
    this.cartService.getProducts()
    .subscribe(res=>{
      this.totalItem = res.length;
    })
    this.checkuser=JSON.parse(localStorage.getItem("userdata"));
    this.username=this.checkuser.username;
  }
  doSearch(value:string)
  {
    console.log(`value=${value}`);
    this.router.navigateByUrl(`/search/${value}`);
  }
  logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("userdata");
    localStorage.removeItem("response");
    console.log(localStorage.getItem("token"));
    console.log(localStorage.getItem("userdata"));
    this.router.navigate(['']);

  }



  
  

}

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
  public totalItem : number = 0;
  
  constructor(private router:Router,private cartService:CartService) { }

  ngOnInit(): void {
    this.cartService.getProducts()
    .subscribe(res=>{
      this.totalItem = res.length;
    })
    this.checkuser=JSON.parse(localStorage.getItem("userdata"));
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

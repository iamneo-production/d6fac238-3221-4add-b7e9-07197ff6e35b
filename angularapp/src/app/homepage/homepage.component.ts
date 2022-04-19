import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../entity/product';
import { CartService } from '../services/cart.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  products:Product[];
  pid:number;
  isopen:boolean=false;
  product:Product;
  addToCart:any;
  quantity:number;
  constructor(private productService:ProductService,private router:Router,private cartService:CartService) {
    
   }

  ngOnInit(): void {
    
    this.listProducts();
  }
  listProducts(){
    this.productService.getProductList().subscribe(
      data=>{
        this.products=data;
      },
      error=>{
        console.log(error);
      }
    );
   }
   show(){
     this.isopen=!this.isopen;
   }
   showdetails(pid:number){
    this.pid=pid;
    this.router.navigate(['productdetail',pid]);
     
   }
   toCart(temp:number){
    this.pid=temp;
    this.quantity=1;
    console.log(this.pid,this.quantity);
   this.cartService.addToCart(this.pid,this.quantity);

 }

}

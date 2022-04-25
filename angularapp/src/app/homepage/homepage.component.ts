import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  searchMode:boolean=false;
  constructor(private productService:ProductService,private router:Router,private cartService:CartService,private route:ActivatedRoute) {
    
   }

  ngOnInit(): void {
    
    this.listProducts();
  }
  listProducts(){
    this.searchMode=this.route.snapshot.paramMap.has('keyword');
    
    if(this.searchMode)
    {
       console.log(this.searchMode);
       this.handleSearchProducts();
    }
    else
    
    this.showProducts();
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
 showProducts(){
     
  this.productService.getProductList().subscribe(
   data=>{
     this.products=data;
   },
   error=>{
     console.log(error);
   }
 );

}
 handleSearchProducts()
 {
   const theKeyword:string=this.route.snapshot.paramMap.get('keyword');
   console.log(theKeyword);
   this.productService.searchProducts(theKeyword).subscribe(
     data =>{
       this.products=data;
       
     }
   );
 }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '../entity/cart';
import { CartItemDto } from '../entity/cartitemdto';
import { CartService } from '../services/cart.service';
import { ProductService } from '../services/product.service';
declare let alertify:any;
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  temp:any;
  public productlength:number;
  public products : any=[];
  public grandTotal : number;
  public cart=new Cart();
  itemDto=new CartItemDto();

  constructor(private cartService:CartService,private productService:ProductService,private router:Router) { }

  ngOnInit(): void {
    this.cartService.getProductsByUser()
    .subscribe(res=>{
      console.log(res);
      this.temp = res;
      this.cart=this.temp;
      this.products=this.cart.items;
      console.log(this.products);
      this.grandTotal=this.cart.total;
      this.productlength=this.products.length
      //this.grandTotal = this.cartService.getTotalPrice();
    })
  }
  add(item: any){
    
    if(item.quantity >=5){
      alertify.error('Max Limit 5 Only per Product')
    }
    if(item.quantity!=5){
      item.quantity += 1;
      this.itemDto.cartItemId=item.cartItemid;
      this.itemDto.quantity=item.quantity;
      this.cartService.updateCart(this.itemDto).subscribe(
        data=>{
          this.temp=data;
          this.ngOnInit();
        }
      );
      
    }
   
    
  }
  minus(item: any){
    if(item.quantity==1){
      this.removeItem(item.cartItemid);
    }
    if(item.quantity!=1){
      item.quantity -= 1; 
      this.itemDto.cartItemId=item.cartItemid;
      this.itemDto.quantity=item.quantity;
      this.cartService.updateCart(this.itemDto).subscribe(
        data=>{
          this.temp=data;
          this.grandTotal-=item.price;
          this.ngOnInit();
        }
      );
    }
    
   
  }
  minusone(item:any){
   
      this.cartService.removeCartItem(item);
    
  }
  removeItem(cartItemid:number){
    console.log(cartItemid);
    this.cartService.removeCartItem(cartItemid).subscribe(
      data=>{
        temp=>data;
        this.ngOnInit();
      },error=>{
        console.log(error);
      }
    );

  }
  emptycart(){
    this.cartService.removeAllCart();
  }
  toCheckout(){
    this.router.navigate(['checkout']);
  }
}

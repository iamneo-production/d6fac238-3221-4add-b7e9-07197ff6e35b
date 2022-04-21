import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from '../entity/address';
import { Cart } from '../entity/cart';
import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';
import { ProductService } from '../services/product.service';
declare let alertify:any;
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  temp:any;
  public address=new Address();
  public cart=new Cart();
  public grandTotal : number;
  public products : any = [];
  constructor(private cartService:CartService,private productService:ProductService,private router:Router,private orderService:OrderService ) { }

  ngOnInit(): void {
    this.cartService.getProductsByUser()
    .subscribe(res=>{
      console.log(res);
      this.temp = res;
      this.cart=this.temp;
      this.products=this.cart.items;
      console.log(this.products);
      this.grandTotal=this.cart.total;
      //this.grandTotal = this.cartService.getTotalPrice();
    })
  }
  onSubmit(){
    this.orderService.placeOrder(this.address).subscribe(
      data=>{
        console.log(data);
        alertify.success('Order Placed');
        this.router.navigate(['myorders'])
      }
      ,error=>{
        console.log(error);
        alertify.error('Order not Placed')
      }
    )
  }
}

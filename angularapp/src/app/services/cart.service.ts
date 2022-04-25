import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { AddToCart } from '../entity/addtocart';
import { Cart } from '../entity/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  public cartItemList : any =[]
  public productList = new BehaviorSubject<any>([]);
  id:number;
  cartItemid:number;
  temp:any;
  cart=new Cart();
  add=new AddToCart();
  constructor(private httpClient:HttpClient) { }
  getProducts(){
    return this.productList.asObservable();
  }
  getProductsByUser(){
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    this.id=this.temp.id;
    
    return this.httpClient.get(apiUrl+"list/"+this.id)
  }
  addToCart(pid:number,quantity:number){
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    this.id=this.temp.id;
    this.add.productId=pid;
    this.add.quantity=quantity;
    console.log(this.id,this.add);
    this.httpClient.post(apiUrl+"cart/add/"+this.id,this.add).subscribe(
      data=>{
        this.temp=data;
        this.cart=this.temp;
        this.cartItemList=this.cart.items;
       
      },error=>{
        console.log(error);
      }
    );
   
  }

 updateCart(item:any){
  return this.httpClient.post(apiUrl+"edit/"+this.id,item);
 }

  removeAllCart(){
    this.cartItemList = []
    this.productList.next(this.cartItemList);
  }
  removeCartItem(cartItemid:number){
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    this.id=this.temp.id;
    this.cartItemid=cartItemid;
    return this.httpClient.get(apiUrl+"delete/"+this.cartItemid+"/"+this.id);
    
  }
}

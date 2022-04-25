import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiUrl } from 'src/environments/environment';
import { Address } from '../entity/address';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  temp:any;
  id:number;
  constructor(private httpClient:HttpClient) { }
  getOrderList(){
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    this.id=this.temp.id;
    
    return this.httpClient.get(apiUrl+"listorder/"+this.id)
  }
  placeOrder(address:Address){
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    this.id=this.temp.id;

    return this.httpClient.post(apiUrl+"placeorder/"+this.id,address);
  }
  getOneOrder(id:number){
    return this.httpClient.get(apiUrl+"oneorder/"+id);
  }
  getAllOrders(){
    return this.httpClient.get(apiUrl+"allorders");
  }
  cancelOrder(id:number){
    return this.httpClient.get(apiUrl+"cancelorder/"+id);
  }
}

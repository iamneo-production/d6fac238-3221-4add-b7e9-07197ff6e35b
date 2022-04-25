import { Component, OnInit } from '@angular/core';
import { Order } from '../entity/order';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['./admin-order.component.css']
})
export class AdminOrderComponent implements OnInit {
  public page=1;
  public pageSize=3;
  temp:any=[];
  orders:Array<Order>;
  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
    this.orderService.getAllOrders()
    .subscribe(res=>{
      console.log(res)
      this.temp=res;
      this.orders=this.temp;
      
 
    },
    error=>{
      console.log(error)
    })
  }

}

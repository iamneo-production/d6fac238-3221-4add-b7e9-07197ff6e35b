import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders:any=[];
  public ordersItems:any=[];
  address:any;
  constructor(private orderService:OrderService,private router:Router) { }

  ngOnInit(): void {
   this.orderService.getOrderList()
   .subscribe(res=>{
     console.log(res)
     this.orders=res;
     

   },
   error=>{
     console.log(error)
   })
   
  }
  viewOrder(id:number){
    this.router.navigate(['vieworder',id])
  }
  

}

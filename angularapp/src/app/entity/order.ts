import { User } from "./user";

export class Order {
    id:number;
    orderItems:any [];
    createdDate:any;
    address:any
    totalPrice:any ; 
    user:User
}

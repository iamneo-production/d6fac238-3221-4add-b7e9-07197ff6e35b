import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../entity/product';
import { Review } from '../entity/review';
import { CartService } from '../services/cart.service';
import { ProductService } from '../services/product.service';
import { ReviewService } from '../services/review.service';
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  temp:any;userid:number;pid:number;
  product=new Product();rating:number;
  addreview=new Review();editreview=new Review();
  temp1:any;quantity:number=1;
  review:any=[]; overallRating:number=0;
  arr=[1,2,3,4,5];reviewid:number;
  constructor(private route:ActivatedRoute,private productService:ProductService,private router:Router,private cartService:CartService,private reviewService:ReviewService) { }

  ngOnInit(): void {
    this.temp=JSON.parse(localStorage.getItem("userdata"));
    console.log(this.temp);
    this.userid=this.temp.id;
    console.log(this.userid);
    this.pid=this.route.snapshot.params['pid'];
    this.getDetails();
    this.getReviews();
  }
  back(){
    this.router.navigate(['home']);
  }
  add()
  {
   this.quantity+=1;
  }
  minus()
  {
    if(this.quantity!=1)
    {
   this.quantity-=1;
    }
  }
  toCart(){
  
   this.cartService.addToCart(this.pid,this.quantity);

 }
 buynow(){
  
 this.cartService.addToCart(this.pid,this.quantity);
this.router.navigate(['cart']);
}
  getDetails(){
    this.productService.getProductById(this.pid).subscribe(
      data=>{
        this.product=data;
        console.log(this.product);
      },error=>{
        console.log(error);
      }
    );
  }
  getReviews(){
    this.reviewService.getReviews(this.pid).subscribe(
      data=>{
        this.review=data;
        let sum=0;
        console.log(this.review);
        for(let i=0;i<this.review.length;i++){
          sum+=this.review[i].rating;
        
        }
        this.overallRating=sum/this.review.length;
        console.log(this.overallRating);

      },
      error=>{
        console.log(error)
      }
    )
  }
  addReview(){
    let user=JSON.parse(localStorage.getItem("userdata"));
    this.addreview.productid=this.pid;
    this.addreview.userid=user.id;
    this.addreview.rating=this.rating;
    console.log(this.addreview);
    this.reviewService.addReview(this.addreview).subscribe(
      data=>{
        console.log(data);
        this.reloadData();
      },
      error=>{console.log(error)}
    )
  }
  reloadData(){
    setTimeout(function(){
      location.reload();},800);
  }
  getreview(id:number){
    this.reviewService.getReview(id).subscribe(
      data=>{
        this.temp1=data;
        this.editreview=this.temp1;
        this.reviewid=this.editreview.id
      },
      error=>{
        console.log(error);
      }
    )
  }
  editReview(){
    
    this.reviewService.editReview(this.editreview).subscribe(
      data=>{
        console.log(data);
      }
    );
  }
  deleteReview(){
    this.reviewService.deleteReview(this.reviewid).subscribe(
      data=>{
        console.log(data)
      },error=>{
        console.log(error)
      }
    )
  }

}

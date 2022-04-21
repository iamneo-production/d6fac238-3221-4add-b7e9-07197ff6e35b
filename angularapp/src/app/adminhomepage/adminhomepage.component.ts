import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../entity/product';
import { ProductService } from '../services/product.service';
declare let alertify:any;
@Component({
  selector: 'app-adminhomepage',
  templateUrl: './adminhomepage.component.html',
  styleUrls: ['./adminhomepage.component.css']
})
export class AdminhomepageComponent implements OnInit {
  public page=1;
  public pageSize=3;
  pid:number;
  products:Array<Product>;
  isShown:boolean=false;
  product=new Product();
  product1=new Product();
  pro:Product;
  constructor(private productService:ProductService,private router:Router) { }

  ngOnInit(): void {
    this.productService.getProductList().subscribe(
      data=>{
        this.products=data;
      },
      error=>console.log(error)
    )
  }

  reloadData(){
    setTimeout(function(){
      location.reload();},800);
  }

  listProducts(){
    this.productService.getProductList().subscribe(
      data=>{
        this.products=data;
      },
      error=>console.log(error)
    );
   }

   show(pid:number){
    this.pid=pid;
    this.isShown=true;
    this.productService.getProductById(this.pid).subscribe(
      data=>{
        this.product=data;
        console.log(this.product);
       
      },
      error=>
        console.log(error)
      );
     
   }
   back(){
    this.isShown=false;
  }

  newProduct(){
    this.productService.addnewProduct(this.product1).subscribe(
      data=>{
        console.log("Done");
        alertify.success("Product Added");
        this.product1=new Product();
        this.reloadData();
        
      },
      error=>{
        console.log("Not Done")
        alertify.error("Product Not Added");
      }
    )
  }

  editProductLocal(){
    
    this.productService.editProduct(this.pid,this.product).subscribe(
      data=>{
        console.log(data);
        this.product=new Product();
        alertify.success("Product Updated");
        this.reloadData();
      },
      error => {
        console.log(error)
        alertify.error("Product not Updated")
      }
    );
  }
  deleteProduct(){
    this.productService.deleteProductById(this.pid).subscribe(
      data=>{
        console.log('deleted');
        alertify.success("Product deleted");
        this.reloadData();
      },
      error=> {console.log(error);
        alertify.error("Product not Deleted");
      }
    )
  }


}

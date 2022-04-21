import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Product } from '../entity/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor( private httpClient:HttpClient) { }
  getProductList():Observable<any>{
    return this.httpClient.get(apiUrl+'all');
  }
  
  public addnewProduct(product:Product):Observable<any>{
    return this.httpClient.post(apiUrl+"addproduct",product);
  }
   public getProductById(pid:number):Observable<any>{
    return this.httpClient.get(apiUrl+"getproduct/"+pid);
   }
  public editProduct(pid:number,product:Product):Observable<any>{
    return this.httpClient.post(apiUrl+"productEdit/"+pid,product);
  }
  public deleteProductById(pid:number){
    return this.httpClient.get(apiUrl+"delete/"+pid);
  }
}

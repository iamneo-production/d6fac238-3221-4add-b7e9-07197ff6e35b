import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Review } from '../entity/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  constructor(private httpClient:HttpClient) { }
  public getReviews(pid:number){
    return this.httpClient.get(apiUrl+"listreview/"+pid);
  }
  public addReview(review:Review):Observable<any>{
    return this.httpClient.post(apiUrl+"addreview",review);
  }
  public getReview(id:number){
    return this.httpClient.get(apiUrl+"getonereview/"+id);
  }
  public editReview(review:Review):Observable<any>{
    return this.httpClient.post(apiUrl+"editreview",review);
  }
  public deleteReview(id:number){
    return this.httpClient.get(apiUrl+"deletereview/"+id);
  }
}

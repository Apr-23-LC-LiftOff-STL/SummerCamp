import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Camp } from './camp';
import { Observable } from 'rxjs';
import { UserService } from './_services/user.service';

@Injectable({
  providedIn: 'root'
})
export class CampService {

  private baseURL = "http://localhost:8080/api/camps";

  requestHeader = new HttpHeaders(
    { "No-Auth":"True"}
  );

  constructor(private httpClient : HttpClient, private userService : UserService) { }

  getCampsList(): Observable<Camp[]>{
    return this.httpClient.get<Camp[]>(`${this.baseURL}`,{ headers:this.requestHeader});
  }

  createCamp(camp: Camp): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`,camp);
  }

  getUniqueCategoriesArray(): Observable<string[]>{
    return this.httpClient.get<string[]>(`${this.baseURL}/unique-categories`,{ headers:this.requestHeader});
  }

  getSelectedCampsSortedByPrice(categories: string[],order: string): Observable<Camp[]>{
    const params = new HttpParams()
    .set('categories',categories.join(','))
    .set('order', order);

    //return this.httpClient.get<Camp[]>(`${this.baseURL}/price`, { params });
    return this.httpClient.get<Camp[]>(`${this.baseURL}/price`, { headers:this.requestHeader, params });
  }
}

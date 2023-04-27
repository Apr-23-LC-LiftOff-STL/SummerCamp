import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Camp } from './camp';

@Injectable({
  providedIn: 'root'
})
export class CampService {
  //must be the port number for the backend api - Tomcat8080
  private baseUrl = "http://localhost:8080/api/camps";

  constructor(private httpClient: HttpClient) { }

  getCampsList(): Observable<Camp[]>{
      return this.httpClient.get<Camp[]>(this.baseUrl);
    }


  getAll(): Observable<Camp[]> {
      return this.httpClient.get<Camp[]>(this.baseUrl);
    }

    get(id: any): Observable<any> {
      return this.httpClient.get(`${this.baseUrl}/${id}`);
    }

    create(data: any): Observable<any> {
      return this.httpClient.post(this.baseUrl, data);
    }

    update(id: any, data: any): Observable<any> {
      return this.httpClient.put(`${this.baseUrl}/${id}`, data);
    }

    delete(id: any): Observable<any> {
      return this.httpClient.delete(`${this.baseUrl}/${id}`);
    }



}

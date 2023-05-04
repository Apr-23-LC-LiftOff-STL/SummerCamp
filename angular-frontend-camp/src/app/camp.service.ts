import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Camp } from './camp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CampService {

  //must be the port number for the backend api - Tomcat8080
  private baseURL = "http://localhost:8080/api/camps";
  constructor(private httpClient : HttpClient) {}

  getCampsList(): Observable<Camp[]>{
    return this.httpClient.get<Camp[]>(`${this.baseURL}`);
  }

  createCamp(camp: Camp): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, camp);
  }

  getAll(): Observable<Camp[]> {
    return this.httpClient.get<Camp[]>(this.baseURL);
  }

  get(id: any): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.httpClient.post(this.baseURL, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.httpClient.put(`${this.baseURL}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
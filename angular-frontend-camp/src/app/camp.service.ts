import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Camp } from './camp';

@Injectable({
  providedIn: 'root'
})
export class CampService {

  private baseURL = "http://localhost:8080/api/camps";

  constructor(private httpClient: HttpClient) { }
/*
  getCampsList(): Observable<Camp[]>{
    return this.httpClient.get<Camp[]>(`${this.baseURL}`);
  }
  */

  getAll(): Observable<Camp[]> {
      return this.http.get<Camp[]>(baseUrl);
    }

    get(id: any): Observable<Camp> {
      return this.http.get(`${baseUrl}/${id}`);
    }

    create(data: any): Observable<any> {
      return this.http.post(baseUrl, data);
    }

    update(id: any, data: any): Observable<any> {
      return this.http.put(`${baseUrl}/${id}`, data);
    }

    delete(id: any): Observable<any> {
      return this.http.delete(`${baseUrl}/${id}`);
    }



}

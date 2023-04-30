import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Camp } from './camp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CampService {

  private baseURL = "http://localhost:8080/api/camps";
  constructor(private httpClient : HttpClient) { }

  getCampsList(): Observable<Camp[]>{
    return this.httpClient.get<Camp[]>(`${this.baseURL}`);
  }

  createCamp(camp: Camp): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`,camp);
  }
}

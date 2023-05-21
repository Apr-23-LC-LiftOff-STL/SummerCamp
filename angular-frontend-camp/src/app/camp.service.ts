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

  public getCampsList(): Observable<Camp[]>{
    return this.httpClient.get<Camp[]>(`${this.baseURL}`);
  }

  public getCamp(id: any): Observable<Camp>{
    const url = this.baseURL + "/" + id;
    return this.httpClient.get<Camp>(url);
  }
  
 
 

}

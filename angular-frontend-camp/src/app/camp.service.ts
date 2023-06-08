import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Camp } from './camp';

@Injectable({
  providedIn: 'root'
})

export class CampService {
  createCamp(camp: Camp) {
    throw new Error('Method not implemented.');
  }

  //must be the port number for the backend api - Tomcat8080

  private baseURL = "http://localhost:8080/api/v1/camps";
  constructor(private httpClient : HttpClient) { }

  getCampsList(gradeGroup: any): Observable<Camp[]> {
    let gradeGrpParam = new HttpParams().set('gradeGrp', gradeGroup);
    return this.httpClient.get<Camp[]>(this.baseURL, { params: gradeGrpParam });
  }


  public getCamp(id: any): Observable<Camp>{
    const url = this.baseURL + "/" + id;
    return this.httpClient.get<Camp>(url);
  }

  /*getAll(): Observable<Camp[]> {
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
  }*/


}

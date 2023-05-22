import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Camp } from '../ModelInterfaces/camp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CampService {

  //must be the port number for the backend api - Tomcat8080
  private baseURL = "http://localhost:8080/api/v1/camps";
  constructor(private httpClient : HttpClient) {}

  getCampsList(gradeGroup: any): Observable<Camp[]> {
    let gradeGrpParam = new HttpParams().set('gradeGrp', gradeGroup);
    return this.httpClient.get<Camp[]>(this.baseURL, { params: gradeGrpParam });
  }


  createCamp(camp: Camp): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, camp);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/${id}`, { responseType: 'text' });
  }
  


}

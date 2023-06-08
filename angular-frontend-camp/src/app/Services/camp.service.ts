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

  getCampsList(gradeGroup: any, order: string): Observable<Camp[]> {
    let gradeGrpParam = new HttpParams().set('gradeGrp', gradeGroup).set('order', order);
    return this.httpClient.get<Camp[]>(this.baseURL, { params: gradeGrpParam });
  }


  createCamp(camp: Camp): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, camp);
  }
  
  deleteCamp(campId: number): Observable<Object> {
    console.log(campId)
    return this.httpClient.delete(`${this.baseURL}/${campId}`);
 
  }

  updateCamp(id: number,camp: Camp): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, camp);
  }

  getCampById(id: number): Observable<Object> {
    return this.httpClient.get(`${this.baseURL}/${id}`);
  }
  
  getUniqueCategoriesArray(): Observable<string[]>{
    return this.httpClient.get<string[]>(`${this.baseURL}/unique-categories`);
  }

  getSelectedCampsSortedByPrice(categories: string[],order: string, gradeGrp: string): Observable<Camp[]>{
    if(categories.length == 0){
      return this.getCampsList(gradeGrp, order);
    }
    const params = new HttpParams()
    .set('categories',categories.join(','))
    .set('order', order)
    .set('gradeGroup', gradeGrp);
    return this.httpClient.get<Camp[]>(`${this.baseURL}/price`, { params });
  }
  


}

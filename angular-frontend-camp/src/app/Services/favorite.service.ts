import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Camp } from '../ModelInterfaces/camp';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  private baseURL = "http://localhost:8080/api/camps/favorites";

  constructor(private httpClient : HttpClient) { }

  addToFavorites(campId: number, userName: string): Observable<Object> {
    const params = new HttpParams()
      .set('campId', campId.toString())
      .set('userName', userName);
    return this.httpClient.post(`${this.baseURL}/add`, null, { params });
  }


  removeFromFavorites(campId: number, userName: string): Observable<Object> {
    const params = new HttpParams()
      .set('campId', campId.toString())
      .set('userName', userName);
    return this.httpClient.post(`${this.baseURL}/remove`, null, { params });
  }

  viewFavorites(userName: string): Observable<Camp[]> {
    const params = new HttpParams()
      .set('userName', userName);
    return this.httpClient.get<Camp[]>(`${this.baseURL}/view`, { params });
  }


}

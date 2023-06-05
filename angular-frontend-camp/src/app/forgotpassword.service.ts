import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class ForgotpasswordService {
  
  apiUrl!: "http://localhost:8080/";
  

  constructor(private httpClient:HttpClient) { }

  public getUserName(token: any): Observable<User>{
    const url = this.apiUrl + "/" + token;
    return this.httpClient.get<User>(url);
  }


  // getForgotPassword(email: any) {
  //   return this.http.post(`${this.apiUrl}/forgot-password`, { email });
  // }
}

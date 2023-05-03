import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from 'src/app/ModelInterfaces/user';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginApiURL = "http://localhost:8080";

  constructor(private httpClient : HttpClient) { }

  public getAllUsers(): Observable<User>{
    return this.httpClient.get<User>(`${this.loginApiURL}/users/all`)
  }

  create(data: any): Observable<any> {
    return this.httpClient.post(this.loginApiURL, data);
  }

  
  }
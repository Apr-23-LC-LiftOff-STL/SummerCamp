import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/ModelInterfaces/user';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private registerURL = "http://localhost:8080/registerNewUser";

  requestHeader = new HttpHeaders(
    { "No-Auth":"True"}
  );

  constructor(private httpClient: HttpClient) {}

  createNewUser(user: User): Observable<any> {
    return this.httpClient.post(`${this.registerURL}`, user, { headers:this.requestHeader});
  }

}

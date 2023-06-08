import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from 'src/app/ModelInterfaces/user';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private loginApiURL = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  public verifyUser(): void {}

  create(data: any): Observable<any> {
    return this.httpClient.post(this.loginApiURL, data);
  }
}

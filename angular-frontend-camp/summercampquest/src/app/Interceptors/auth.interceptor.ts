import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from '../Services/login.service';
import { CampService } from '../camp.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  

  constructor(private loginService : LoginService, private campService : CampService) {}

  intercept(httpRequest: HttpRequest<any>, httpHandler: HttpHandler): Observable<HttpEvent<any>> {

    if(httpRequest.url.includes(`${this.loginService.host}/auth/login`)){
      return httpHandler.handle(httpRequest);
    }

    if(httpRequest.url.includes(`${this.loginService.host}/auth/register`)){
      return httpHandler.handle(httpRequest);
    }

    if(httpRequest.url.includes(`${this.campService.getBaseUrl()}`)){
      return httpHandler.handle(httpRequest);
    }

    this.loginService.loadToken();
    const token = this.loginService.getToken();
    const request=httpRequest.clone({setHeaders: { Authorization:`Bearer ${token}`}})
    return httpHandler.handle(request);
    
  }


}

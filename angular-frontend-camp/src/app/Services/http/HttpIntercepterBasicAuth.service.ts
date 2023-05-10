import { HttpClient, HttpInterceptor, HttpHandler,HttpRequest,HttpEvent,HttpHeaders } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';
import { LoginService } from '../login.service';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor{

  constructor(private loginService: LoginService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const username = this.loginService.getUsername();
    const password = this.loginService.getPassword();
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(username + ':' + password)
    });
    const authReq = req.clone({headers});
    return next.handle(authReq);
  }

//   constructor(@Inject('USERNAME') private username: string,
//   @Inject('PASSWORD') private password: string) {}
// intercept(req: HttpRequest<any>, next: HttpHandler) {
//   const xhr = req.clone({
//     headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
//   });
//   return next.handle(xhr);
// }
// intercept(req: HttpRequest<any>, next: HttpHandler){
// let basicAuthHeaderString =
// 'Basic ' + window.btoa(this.username + ':' + this.password);

// req = req.clone({
// setHeaders : {
// Authorization : basicAuthHeaderString
// }
// })
// return next.handle(req);


}
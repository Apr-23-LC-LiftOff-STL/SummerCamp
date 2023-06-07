//this class is responsible for adding header to the request

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { UserAuthService } from "../_services/user-auth.service";
import { Router } from "@angular/router";
import { catchError } from "rxjs/operators";
import { Injectable } from "@angular/core";

//by fetching jwttoken of the account from local storage(i.e. from UserAuthService class)
@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private userAuthService: UserAuthService,
    private router: Router){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if(req.headers.get('No-Auth') === 'True'){
            return next.handle(req.clone());
        }
        //fetch jwt token
        const token = this.userAuthService.getToken();

        req = this.addToken(req,token);
        return next.handle(req).pipe(
            catchError(
                (err:HttpErrorResponse) => {
                        console.log(err.status);
                        //user is not logged in 
                        if(err.status === 401){
                            this.router.navigate(['/login']);
                        } else if(err.status === 403){
                            this.router.navigate(['/forbidden']);
                        }
                        return throwError("Some thing is wrong");
                }
            )
        );
       
    }
     //function that will take this token and adds to header
     private addToken(request:HttpRequest<any>, token:string){
        return request.clone(
            {
                setHeaders: {
                    Authorization: `Bearer ${token}`
                }
            }
        );
        
     }   
}
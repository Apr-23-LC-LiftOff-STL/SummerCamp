import { HttpHeaders, HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { User } from '../Models/user';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private host = 'http://localhost:8080';
  private serviceToken: string | null = null;
  private loggedInUsername: string | null = null;
  private jwtHelper = new JwtHelperService();


  constructor(private http: HttpClient) {}

    public login(user: User): Observable<HttpResponse<any> | HttpErrorResponse>{

      return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/auth/login`, user,{observe: 'response'})

    }

    public logOut(): void{
      
      this.serviceToken = null;
      this.loggedInUsername = null;
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      localStorage.removeItem('users');

    }

    public register(user:User): Observable<User | HttpErrorResponse>{

      return this.http.post<User | HttpErrorResponse>(`${this.host}/auth/register`, user,{observe: 'response'});
    
    }


    public saveToken(token:string | null):void{
      
      if(token){
        this.serviceToken = token;
        localStorage.setItem('token', token);
      } else{
        this.serviceToken = null;
        localStorage.removeItem('token');
      }

    }


    public addUserToLocalCache(user:User):void{
      localStorage.setItem('user', JSON.stringify(user));
    }
    
    public getUserFromLocalCache(): User | null {

      const userString: string | null = localStorage.getItem('user');
      if (userString) {
        const parsedUser: User = JSON.parse(userString);
        return parsedUser;
      }
      return null;
    }


    public loadToken(): void{

      this.serviceToken = localStorage.getItem('token');
    
    }

    public getToken(): string | null{

      if(this.serviceToken){
        return this.serviceToken;
      }
      return null;
    }

    public isLoggedIn(): boolean{
      // Requires package - npm i @auth0/angular-jwt for JWTHelperService 

      this.loadToken();
       
      let thisToken = this.serviceToken;
      
      if(thisToken != null && thisToken != '') {

        let thisDecodedToken = this.jwtHelper.decodeToken(thisToken).sub;

        if(thisDecodedToken != null || thisDecodedToken != '') {
          if(!this.jwtHelper.isTokenExpired(this.serviceToken)){
            
            this.loggedInUsername = thisDecodedToken;
            return true;
          }
        }
      } 
        this.logOut();
        return false;
    }


}

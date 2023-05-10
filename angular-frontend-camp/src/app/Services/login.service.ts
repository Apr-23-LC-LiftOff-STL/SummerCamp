import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { User } from '../Models/user';
import { Observable } from 'rxjs';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseURL = 'http://localhost:8080/api/auth';
  private registerURL = 'http://localhost:8080/api/auth/register';

  user!:User;

  authenticated = false;

  private credentials = {username: '', password: ''};
  constructor(private http: HttpClient) { }

  register(user: User) : Observable<any>{
    return this.http.post<User>(this.registerURL, user)
  }
 
  getUsername(): string {
    // return the username from your credentials object
    return this.credentials.username;
  }
  getPassword():string{
    return this.credentials.password;
  }

  
authenticate(credentials: { username: any; password: any; } | undefined, callback: { (): void; (): any; } | undefined) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get(`${this.baseURL}/login`, {headers: headers}).subscribe(response => {
            if (response['constructor']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }
    
/* 
  public login (username: string, password: string){
    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    let headers = new HttpHeaders({
      Authorization: basicAuthHeaderString
    })

    return this.http.get(`${this.baseURL}`, { headers, responseType: 'text' as 'json'});
  }

  getUsers(){
    let username="test"; 
    let password="password";

    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    const headers = new HttpHeaders({Authorization: basicAuthHeaderString})

    return this.http.get(`${this.baseURL}/login`, { headers})
  }


  verifyCredentials(username: string, password: string) {

    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    let headers = new HttpHeaders({
      Authorization: basicAuthHeaderString
    })

    return this.http
    .get(`${this.baseURL}/login`, { headers })
    .pipe(
      map(data => {
        sessionStorage.setItem('authenticatedUser', username);
        sessionStorage.setItem('token', basicAuthHeaderString);
        return data;
          }
        )
      );
    
  }

 */

}

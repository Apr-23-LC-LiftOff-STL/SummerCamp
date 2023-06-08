import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  

  //this class is used to store role and jwt token of user/admin account in local storage
  //to check and grant access/restriction for the account to the requested urls
  constructor() { }

  public setAccountName(userName: string) {
    localStorage.setItem('userName',userName);
  }

  public getAccountName(): string {
    return localStorage.getItem('userName') as string;
  
  }

  public setRoles(roles:[]){
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): string[] {
    const rolesString = localStorage.getItem('roles') as string;
    return JSON.parse(rolesString);
  }
  
  public setToken(jwtToken: string){
    localStorage.setItem('jwtToken',jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem('jwtToken') as string;
  
  }


  public clear(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      localStorage.clear();
      console.log("cleared local storage");
      resolve();
    });
  }

  //if roles and jwt token is present in local storage => user logged in
  //if not, then user not logged in
  public isLoggedIn(){
    return this.getRoles() && this.getToken();
  }
}

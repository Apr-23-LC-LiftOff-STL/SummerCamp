import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathURL = "http://localhost:8080";

  requestHeader = new HttpHeaders(
    { "No-Auth":"True"}
  );

  constructor(private httpClient : HttpClient,
    private userAuthService: UserAuthService) { }

  public login(loginData: any) {
    return this.httpClient.post(`${this.pathURL}/authenticate`,loginData, { headers:this.requestHeader});
  }

  //check whether the role being passed in condition and the role user has, matches or not.
  //if the role match occurs => true => user can access the link/page (i.e. visible) 
  //if the role passed and user's role does not match => false => link is disabled/page access is forbidden for the user


  public roleMatch(allowedRoles: string[]) : boolean{
      let isMatch = false;
      const userRoles: any = this.userAuthService.getRoles();

      if(userRoles != null && userRoles){
        for(let i=0;i<userRoles.length;i++){
          for(let j=0;j<allowedRoles.length;j++){

            if(userRoles[i].roleName === allowedRoles[j]){
              isMatch = true;
              return isMatch;
            } 
            /*else {
              return isMatch;
            }*/
          }
        }
      }
      return isMatch;
  }



}

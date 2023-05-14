import { LoginService } from './../Services/login.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { User } from '../Models/user';
import { Subscription } from 'rxjs';
import { HeaderType } from '../Models/Enum/header-type.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  private subscriptions:Subscription[] = [];


  constructor(
    private router:Router, 
    private authenticationService:LoginService){}

  ngOnInit() {

    if(this.authenticationService.isLoggedIn()){
      this.router.navigateByUrl('/login-landing');
    }else{
      this.router.navigateByUrl('/login');
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach( 
      sub => sub.unsubscribe());
      
  }
 
  public login(user: User): void {
    console.log(user);
    this.subscriptions.push(
      this.authenticationService.login(user).subscribe(
        (response: HttpResponse<User> | HttpErrorResponse) => {
          if (response instanceof HttpResponse) {
            const token = response.headers.get('Jwt-token');
            this.authenticationService.saveToken(token);
            if (response.body) {
              this.authenticationService.addUserToLocalCache(response.body);
            }
            this.router.navigateByUrl('/login-landing');
          } else if (response instanceof HttpErrorResponse) {
            console.log(response.error.message);
          }
        }
      )
    );
  }
  
 



  
}
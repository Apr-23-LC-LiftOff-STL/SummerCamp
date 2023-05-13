import { LoginService } from './../Services/login.service';
import { HttpIntercepterBasicAuthService } from '../Services/http/HttpIntercepterBasicAuth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};

  errorMessage = 'Invalid Credentials'
  invalidLogin = false
  message:any

  constructor(private router: Router,
    private loginService: LoginService,
    private http: HttpClient) {
      this.loginService.authenticate(undefined, undefined);}


  ngOnInit() {
  }
 
  login() {
    this.loginService.authenticate(this.credentials, () => {
      if (this.loginService.authenticated) {
        this.router.navigateByUrl('/');
      } else {
        this.invalidLogin = true;
      }
    });
    return false;
  }

  
/*
  // attemptLogin(){
  //   let response = this.loginService.login(this.username, this.password);
  //   response.subscribe(
  //     data=>{
  //       this.message=data;
  //       this.router.navigate([""])
  //     }
  //   )
  // }

  // attemptLogin() {
  //   this.httpInterceptor = new HttpIntercepterBasicAuthService(this.username, this.password);
  //   let response = this.loginService.login(this.username, this.password);
  //   response.subscribe(
  //     data => {
  //       this.message = data;
  //       this.router.navigate(['/login-landing']);
  //     }
  //   );
  // }
*/
 
  // verifyCredentials() {
   
  //   this.loginService.verifyCredentials(this.username, this.password)
  //       .subscribe(
  //         data => {
  //           console.log(data)
  //           this.router.navigate(['welcome', this.username])
  //           this.invalidLogin = false      
  //         },
  //         error => {
  //           console.log(error)
  //           this.invalidLogin = true
  //         }
  //       )
  // }

}
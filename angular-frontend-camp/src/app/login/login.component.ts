import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  //loginForm parameter type is NgForm
  login(loginForm: NgForm){
    this.userService.login(loginForm.value).subscribe(
      (response : any) => {
        console.log(response);
        console.log(response.user.userName);
        //console.log(response.jwtToken);
        //console.log(response.user.role);
        this.userAuthService.setRoles(response.user.role);
        this.userAuthService.setToken(response.jwtToken);
        this.userAuthService.setAccountName(response.user.userName);

        const role = response.user.role[0].roleName;
        //if account login is success, then redirect the account to respective url path
        if(role === 'Admin' || role === 'User'){
          this.router.navigate(['/camps']);
        }
      },
      (error) => {
        console.log(error);
      }
    );
 }

}

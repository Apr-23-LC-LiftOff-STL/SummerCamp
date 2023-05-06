import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../Models/user';
import { LoginService } from '../Services/login.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent{

  user!: User;

  constructor(private loginService: LoginService){}

  public verifyUser():void {
    this.loginService.verifyUser();
  }
}

import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../ModelInterfaces/user';
import { LoginService } from '../Services/login.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit{

  users!: User; 

  constructor(private registrationService: LoginService){}

  ngOnInit(): void {
    this.createUsers();
  }


  public createUsers():void {
    this.registrationService.create('formdata');
  }

  
}

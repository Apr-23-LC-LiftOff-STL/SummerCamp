import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
  ValidationErrors,
} from '@angular/forms';
import { User } from '../Models/user';
import { RegistrationValidators } from '../Validators/registration-validators';
import { Subscription } from 'rxjs';
import { LoginService } from './../Services/login.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { HeaderType } from '../Models/Enum/header-type.enum';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit, OnDestroy {

  registrationFormGroup!: FormGroup;
  private subscriptions:Subscription[] = [];

  

  constructor(private formBuilder: FormBuilder,
              private router:Router,  
              private registerService: LoginService) {}

  ngOnInit(): void {
    if(this.registerService.isLoggedIn()){
      this.router.navigateByUrl('/login-landing');
    }
    // this.registrationFormGroup = this.formBuilder.group({
    //   user: this.formBuilder.group({
    //     firstName: new FormControl('', [
    //       Validators.required,
    //       RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
    //     ]),
    //     lastName: new FormControl('', [
    //       Validators.required,
    //       RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
    //     ]),
    //     email: new FormControl('', [
    //       Validators.email,
    //       RegistrationValidators.notOnlyWhitespace,
    //     ]),
    //     age: new FormControl('', [Validators.required]),
    //     grade: new FormControl('', [Validators.required]),
    //     phone: new FormControl('', [
    //       Validators.required,
    //       RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
    //     ]),
    //     username: new FormControl('', [
    //       Validators.required,
    //       RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
    //     ]),
    //     password: new FormControl('', [
    //       Validators.required,
    //       RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
    //     ])
    //   })
    // });
  }


  public register(user: User): void {
    console.log(user);
    this.subscriptions.push(
      this.registerService.register(user).subscribe(
        (response: User) => {
            console.log(response)
            //this.router.navigateByUrl('/login-landing');
          }, (errorResponse: HttpErrorResponse) =>{
            console.log(errorResponse.error.message);
          }
      )
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach( 
      sub => sub.unsubscribe());
      
  }

  // onSubmit() {
  //   if (this.registrationFormGroup.invalid) {
  //     this.registrationFormGroup.markAllAsTouched();
  //     return;
  //   }

  //   this.loginService.register(this.registrationFormGroup.value)
    
  //   console.log(this.registrationFormGroup?.value)
  // } //onSubmit method





  get firstName() {
    return this.registrationFormGroup.get('user.firstName');
  }
  get lastName() {
    return this.registrationFormGroup.get('user.lastName');
  }
  get email() {
    return this.registrationFormGroup.get('user.email');
  }
  get username() {
    return this.registrationFormGroup.get('user.username');
  }
  get password() {
    return this.registrationFormGroup.get('user.password');
  }
  get phone() {
    return this.registrationFormGroup.get('user.phone');
  }
  get grade() {
    return this.registrationFormGroup.get('user.grade');
  }
  get age() {
    return this.registrationFormGroup.get('user.age');
  }
}

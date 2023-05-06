import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
  ValidationErrors,
} from '@angular/forms';
import { User } from '../Models/user';
import { LoginService } from '../Services/login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { RegistrationValidators } from '../Validators/registration-validators';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  registrationFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.registrationFormGroup = this.formBuilder.group({
      user: this.formBuilder.group({
        firstName: new FormControl('', [
          Validators.required,
          RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
        ]),
        lastName: new FormControl('', [
          Validators.required,
          RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
        ]),
        email: new FormControl('', [
          Validators.email,
          RegistrationValidators.notOnlyWhitespace,
        ]),
        age: new FormControl('', [Validators.required]),
        grade: new FormControl('', [Validators.required]),
        phone: new FormControl('', [
          Validators.required,
          RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
        ]),
      }),
      login: this.formBuilder.group({
        username: new FormControl('', [
          Validators.required,
          RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
        ]),
        password: new FormControl('', [
          Validators.required,
          RegistrationValidators.requiredFieldMinSize2NotOnlyWhitespace,
        ]),
      }),
    });
  }

  onSubmit() {
    if (this.registrationFormGroup.invalid) {
      this.registrationFormGroup.markAllAsTouched();
    }
  } //onSubmit method

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

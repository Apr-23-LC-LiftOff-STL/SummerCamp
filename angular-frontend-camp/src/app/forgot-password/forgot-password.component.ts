import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {  FormControl, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ForgotpasswordService } from '../forgotpassword.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
    forgotPasswordForm!: FormGroup;
    submitted = false;
  
    constructor(private forgotpassword:ForgotpasswordService,private http: HttpClient) { }
  
    ngOnInit(): void {
      this.forgotPasswordForm = new FormGroup({
        email: new FormControl('', [Validators.required, Validators.email])
      });
    }
  
    get f() {
      return this.forgotPasswordForm.controls;
    }
  
    onSubmit() {
      this.submitted = true;
      if (this.forgotPasswordForm.invalid) {
        return;
      }
      const email = this.forgotPasswordForm.value.email;
      // this.forgotpassword.getForgotPassword(email).subscribe((response: any) => {
      this.http.post('http://localhost:8080/api/forgot-password', { email }).subscribe((response) => {
        console.log('Forgot password request sent successfully');
        // display a success message to the user
      }, (error: any) => {
        console.error('Failed to send forgot password request:', error);
        // display an error message to the user
      });
    }
  }
  


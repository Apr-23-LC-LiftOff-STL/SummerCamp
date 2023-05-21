import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, NavigationCancel, NavigationEnd, Router } from '@angular/router';
import { ForgotpasswordService } from '../forgotpassword.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent  implements OnInit{

 private token = '';
//  accessToken :string | undefined;
resetPasswordForm!: FormGroup;

submitted = false;
  user: any;

constructor(private http: HttpClient,private route:Router,private forgotPasswordService : ForgotpasswordService){
     route.events.subscribe(s=>{
      if(s instanceof NavigationEnd){
        let url = s.url;
        console.log(url + "," + url.indexOf('token='));
        if (url.indexOf('token=')!=-1) {
          let token = url.substring(url.indexOf('token=')+'token='.length);
          console.log(token);
        }
      }
     });
  
 }

ngOnInit(): void {

  this.forgotPasswordService.getUserName(this.token).subscribe( data => {
    this.user = data;
  });
 
  this.resetPasswordForm = new FormGroup({
    password : new FormControl('', [Validators.required]),
    retypePassword : new FormControl('',[Validators.required])
  });

}

get f() {
  return this.resetPasswordForm.controls;
}

onSubmit() {

  this.submitted = true;

  if (this.resetPasswordForm.invalid) {
//    return;
  }

 

  const password = this.resetPasswordForm.value.password;
  const retypepassword= this.resetPasswordForm.value.retypepassword;
  const token = this.token;
  // this.forgotpassword.getForgotPassword(email).subscribe((response: any) => {
  this.http.post('http://localhost:8080/api/reset-password', { password, retypepassword, token}).subscribe((response) => {
    console.log('Password reset success');
    // display a success message to the user
  }, (error: any) => {
    console.error('Failed to send update password request:', error);
    // display an error message to the user
  });
}
}

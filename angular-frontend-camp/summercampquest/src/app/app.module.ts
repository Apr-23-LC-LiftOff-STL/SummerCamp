import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CampListComponent } from './camp-list/camp-list.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginService } from './Services/login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginLandingComponent } from './login-landing/login-landing.component';
import { RouterModule, Routes } from '@angular/router';
import {AuthInterceptor} from './Interceptors/auth.interceptor';
import { AuthenticationGuard } from './Guard/authentication.guard';

@NgModule({
  declarations: [
    AppComponent,
    CampListComponent,
    LoginComponent,
    RegistrationComponent,
    LoginLandingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [
    LoginService,
    AuthenticationGuard,
    { provide: HTTP_INTERCEPTORS, 
      useClass: AuthInterceptor, 
      multi: true},
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

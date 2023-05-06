import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CampListComponent } from './camp-list/camp-list.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginService } from './Services/login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CampListComponent,
    LoginComponent,
    RegistrationComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule, ReactiveFormsModule],
  providers: [LoginService],
  bootstrap: [AppComponent],
})
export class AppModule {}

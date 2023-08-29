import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CampListComponent } from './camp-list/camp-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CampByCategoryComponent } from './camp-by-category/camp-by-category.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ViewMyFavoritesComponent } from './view-my-favorites/view-my-favorites.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { CreateCampComponent } from './create-camp/create-camp.component';
import { UpdateCampComponent } from './update-camp/update-camp.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { UserService } from './_services/user.service';
import { AboutusComponent } from './aboutus/aboutus.component';
import { CampTipsComponent } from './camp-tips/camp-tips.component';
import { ContactusComponent } from './contactus/contactus.component';
import { CampDetailComponent } from './camp-detail/camp-detail.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { AgGridModule } from 'ag-grid-angular';

@NgModule({
  declarations: [
    AppComponent,
    CampListComponent,
    CampByCategoryComponent,
    CreateCampComponent,
    HeaderComponent,
    FooterComponent,
    ViewMyFavoritesComponent,
    LoginComponent,
    RegistrationComponent,
    UpdateCampComponent,
    ForbiddenComponent,
    AboutusComponent,
    CampTipsComponent,
    ContactusComponent,
    CampDetailComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
     AgGridModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    RouterModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

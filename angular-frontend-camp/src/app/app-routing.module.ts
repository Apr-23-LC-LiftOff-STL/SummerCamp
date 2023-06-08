import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampDetailComponent } from './camp-detail/camp-detail.component';
import { CampListComponent } from './camp-list/camp-list.component';
import { CreateCampComponent } from './create-camp/create-camp.component';
import { CampByCategoryComponent } from './camp-by-category/camp-by-category.component';
import { ViewMyFavoritesComponent } from './view-my-favorites/view-my-favorites.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { UpdateCampComponent } from './update-camp/update-camp.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AuthGuard } from './_auth/auth.guard';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';


const routes: Routes = [
  //javascript object
  {path:'camps', component: CampListComponent},
  {path:'create-camp', component: CreateCampComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  {path:'view-my-favorites', component: ViewMyFavoritesComponent, canActivate:[AuthGuard], data:{roles:['User']} },
  {path:'login', component: LoginComponent},
  {path:'forbidden', component: ForbiddenComponent},
  { path: '', component: CampByCategoryComponent },
  { path: 'register', component: RegistrationComponent },
  {path:'update-camp/:id',component:UpdateCampComponent},
  {path: 'camp-detail/:id', pathMatch: 'full', component: CampDetailComponent  },
  {path:'forgot-password',pathMatch:'full',component:ForgotPasswordComponent},
  {path:'reset-password',pathMatch:'full',component:ResetPasswordComponent},
  { path: 'create-camp', component: CreateCampComponent },
  {path: 'camp-list', pathMatch: 'full', component: CampListComponent  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' }), FormsModule],
  exports: [RouterModule],
})
export class AppRoutingModule {}

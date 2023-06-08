import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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

const routes: Routes = [
  //javascript object
  {path:'camps', component: CampListComponent},
  {path:'create-camp', component: CreateCampComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  {path:'view-my-favorites', component: ViewMyFavoritesComponent, canActivate:[AuthGuard], data:{roles:['User']} },
  {path:'login', component: LoginComponent},
  {path:'forbidden', component: ForbiddenComponent},
  { path: '', component: CampByCategoryComponent },
  { path: 'register', component: RegistrationComponent },
  {path:'update-camp/:id',component:UpdateCampComponent, canActivate:[AuthGuard], data:{roles:['Admin']} }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' }), FormsModule],
  exports: [RouterModule],
})
export class AppRoutingModule {}

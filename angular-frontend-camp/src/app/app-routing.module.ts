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

const routes: Routes = [
  { path: 'camps', component: CampListComponent },
  { path: '', component: CampByCategoryComponent },
  { path: 'create-camp', component: CreateCampComponent },
  {path:'view-my-favorites', component: ViewMyFavoritesComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  {path:'update-camp/:id',component:UpdateCampComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' }), FormsModule],
  exports: [RouterModule],
})
export class AppRoutingModule {}

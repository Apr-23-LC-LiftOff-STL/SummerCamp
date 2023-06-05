import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampDetailComponent } from './camp-detail/camp-detail.component';
import { CampListComponent } from './camp-list/camp-list.component';
import { CreateCampComponent } from './create-camp/create-camp.component';
import { CampByCategoryComponent } from './camp-by-category/camp-by-category.component';
import { ViewMyFavoritesComponent } from './view-my-favorites/view-my-favorites.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

const routes: Routes = [
  { path: 'camps', component: CampListComponent },
  { path: '', component: CampByCategoryComponent },
  {path: 'camp-detail/:id', pathMatch: 'full', component: CampDetailComponent  },
  {path:'forgot-password',pathMatch:'full',component:ForgotPasswordComponent},
  {path:'reset-password',pathMatch:'full',component:ResetPasswordComponent},
  { path: 'create-camp', component: CreateCampComponent },
  {path:'view-my-favorites', component: ViewMyFavoritesComponent},
  {path: 'camp-list', pathMatch: 'full', component: CampListComponent  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

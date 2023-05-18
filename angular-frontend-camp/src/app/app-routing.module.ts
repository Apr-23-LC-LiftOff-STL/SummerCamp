import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampListComponent } from './camp-list/camp-list.component';
import { CreateCampComponent } from './create-camp/create-camp.component';
import { CampByCategoryComponent } from './camp-by-category/camp-by-category.component';
import { ViewMyFavoritesComponent } from './view-my-favorites/view-my-favorites.component';

const routes: Routes = [
  { path: 'camps', component: CampListComponent },
  { path: '', component: CampByCategoryComponent },
  { path: 'create-camp', component: CreateCampComponent },
  {path:'view-my-favorites', component: ViewMyFavoritesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

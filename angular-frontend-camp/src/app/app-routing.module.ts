import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampListComponent } from './camp-list/camp-list.component';
<<<<<<< HEAD
import { CreateCampComponent } from './create-camp/create-camp.component';

const routes: Routes = [
  //javascript object
  {path:'camps', component: CampListComponent},
  {path:'create-camp', component: CreateCampComponent},
  {path:'', redirectTo:'camps', pathMatch:'full'}
=======

const routes: Routes = [
{ path: 'camps', component: CampListComponent }
>>>>>>> 35e97f3ab7eaed2768eb1ca27225994cd9786978
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

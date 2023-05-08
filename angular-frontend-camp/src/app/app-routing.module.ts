import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampListComponent } from './camp-list/camp-list.component';
import { CampByCategoryComponent } from './camp-by-category/camp-by-category.component';

const routes: Routes = [
{ path: 'camps/:gradeGrp', component: CampListComponent },
{ path: '',component:CampByCategoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

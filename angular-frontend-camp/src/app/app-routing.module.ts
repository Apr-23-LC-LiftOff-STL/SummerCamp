import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampListComponent } from './camp-list/camp-list.component';
import { CreateCampComponent } from './create-camp/create-camp.component';
import { LoginComponent } from './login/login.component';

import { FormsModule} from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  //javascript object
  { path: 'camps', component: CampListComponent },
  { path: 'create-camp', component: CreateCampComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: '', redirectTo: 'camps', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes), FormsModule],
  exports: [RouterModule],
})
export class AppRoutingModule {}

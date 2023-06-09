import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CampListComponent } from './camp-list/camp-list.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { CampTipsComponent } from './camp-tips/camp-tips.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'camps', component: CampListComponent },
{ path: 'about', component: AboutComponent },
{ path: 'camp-tips', component: CampTipsComponent },
{ path: 'contact', component: ContactComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

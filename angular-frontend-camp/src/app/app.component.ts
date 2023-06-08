import { UserAuthService } from './_services/user-auth.service';
import { UserService } from './_services/user.service';
import { Component,ViewEncapsulation } from '@angular/core';
import { Camp } from './camp';
import { CampService } from './camp.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation:ViewEncapsulation.None
})

export class AppComponent {
  title = 'Camp Quest';

  public camps : Camp[] = [];

  constructor(private userAuthService: UserAuthService,
  private router: Router,
  public userService: UserService,private campService: CampService){}

  ngOnInit() : void {

  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/camps']);
  }

  public getCamp() {
    this.campService.getCamp(1);// 1 - dummy data
   }

}

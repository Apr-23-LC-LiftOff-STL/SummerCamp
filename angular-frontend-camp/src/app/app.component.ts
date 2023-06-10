import { Component } from '@angular/core';
import { UserAuthService } from './_services/user-auth.service';
import { Router } from '@angular/router';
import { UserService } from './_services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Summer Camp Quest';

  constructor(private userAuthService: UserAuthService, 
  private router: Router,
  public userService: UserService){}

  ngOnInit() : void {

  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/camps']);
  }
}
 
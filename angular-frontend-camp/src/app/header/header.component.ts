import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  userName: string = '';

  constructor(public userAuthService: UserAuthService, 
    private router: Router,
    public userService: UserService){}
  
    ngOnInit() : void {
       
    }
  
    public isLoggedIn(){
      this.userName = this.userAuthService.getAccountName();
      if(!this.userName){
        this.userName = '';
      } 
      console.log("userName: "+this.userName);
      return this.userAuthService.isLoggedIn();
    }


  
    public logout() {
    this.userAuthService.clear()
    .then(() => {
      // Code that relies on cleared localStorage
      this.router.navigate(['']);
    })
    .catch(error => {
      // Handle any errors that occur during the clearing process
      console.error(error);
    });
   
    }
      
}



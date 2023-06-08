import { Component, OnInit } from '@angular/core';
import { FavoriteService } from '../Services/favorite.service';
import { Camp } from '../ModelInterfaces/camp';
import {Location} from '@angular/common';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-view-my-favorites',
  templateUrl: './view-my-favorites.component.html',
  styleUrls: ['./view-my-favorites.component.css']
})
export class ViewMyFavoritesComponent implements OnInit {

  
  favoritesList: Camp[] = [];
  userName : string = '';
  
  constructor(private favoriteService: FavoriteService, private location: Location, private userAuthService: UserAuthService) {}
   

  ngOnInit(): void {
    this.userName = this.userAuthService.getAccountName();
    this.getFavorites();
  }

  private getFavorites() {
    this.favoriteService.viewFavorites(this.userName).subscribe(data => {
      console.log(data);
      this.favoritesList = data;
    },
    (error) => {
    console.log(error);
    });
  }

  goBack() {
    console.log("called goBack fn");
    this.location.back();
  }
  
}

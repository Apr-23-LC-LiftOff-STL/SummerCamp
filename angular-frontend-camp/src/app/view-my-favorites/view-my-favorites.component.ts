import { Component, OnInit } from '@angular/core';
import { FavoriteService } from '../Services/favorite.service';
import { Camp } from '../ModelInterfaces/camp';



@Component({
  selector: 'app-view-my-favorites',
  templateUrl: './view-my-favorites.component.html',
  styleUrls: ['./view-my-favorites.component.css']
})
export class ViewMyFavoritesComponent implements OnInit {

  
  favoritesList: Camp[] = [];
  userName = 'raam';
  
  constructor(private favoriteService: FavoriteService) {}
   

  ngOnInit(): void {
    this.getFavorites();
  }

  private getFavorites() {
    this.favoriteService.viewFavorites(this.userName).subscribe(data => {
      console.log(data);
      this.favoritesList = data;
  });
  }
  
}

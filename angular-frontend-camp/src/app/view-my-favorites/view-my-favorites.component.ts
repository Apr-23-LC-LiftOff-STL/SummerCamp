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
  currentPage: number = 1;
  itemsPerPage: number = 10; // Number of items per page

  
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

  totalPages(): number {
    return Math.ceil(this.favoritesList.length / this.itemsPerPage);
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages()) {
      this.currentPage = page;
    }
  }

  visiblePages(): number[] {
    const total = this.totalPages();
    const start = Math.max(1, this.currentPage - 2);
    const end = Math.min(total, start + 4);
    return Array.from({ length: end - start + 1 }, (_, index) => start + index);
  }  
  
  
}

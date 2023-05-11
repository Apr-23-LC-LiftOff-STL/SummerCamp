import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';
import { FavoriteService } from '../favorite.service';
import { ToastrService } from 'ngx-toastr';
 
@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {
  

  camps: Camp[] = [];
  favoritesList: Camp[] = [];
  userName = 'raam';
  

  constructor(private campService: CampService,  private favoriteService: FavoriteService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getCamps();
    this.getFavorites();
  }
  
  
  private getCamps(){
    this.campService.getCampsList().subscribe(data => {
        this.camps = data;
    });
  }

  private getFavorites(){
    this.favoriteService.viewFavorites(this.userName).subscribe(data => {
        console.log(data);
        this.favoritesList = data;
    });
  }

  addToFavorites(campId: number){
    this.favoriteService.addToFavorites(campId, this.userName).subscribe(() => {
        this.getFavorites();
        this.toastr.info('marked as my favorite!');
    });
  }

  removeFromFavorites(campId: number){
    this.favoriteService.removeFromFavorites(campId, this.userName).subscribe(() => {
        this.getFavorites();
        this.toastr.info('removed from my favorites!');
    });
  }

  isFavorite(campId: number): boolean {
    return this.favoritesList.some(camp => camp.id === campId);
  }

}




import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';
import { FavoriteService } from '../favorite.service';
import { ToastrService } from 'ngx-toastr';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

 
@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {
  

  camps: Camp[] = [];
  uniqueCategories: string[] = [];
  priceOptions: string[] = ['price: low to high','price: high to low'];
  selectedPriceOption: string = this.priceOptions[0];
  selectedItems: string[] = [];
  favoritesList: Camp[] = [];
  userName: string = '';
  
  constructor(private campService: CampService,  private favoriteService: FavoriteService, 
    private toastr: ToastrService, public userAuthService: UserAuthService,
    public userService: UserService) { }

  ngOnInit(): void {

    this.getUniqueCategories();
    this.getSelectedCampsSortedByPrice();
    this.userName = this.userAuthService.getAccountName();
    if(this.userService.roleMatch(['User'])){
      this.getFavorites();
    }
    
  }
  
  
  private getCamps(){
    this.campService.getCampsList().subscribe(data => {
        console.log(data);
        this.camps = data;
    });
  }

  private getUniqueCategories(){
    this.campService.getUniqueCategoriesArray().subscribe(data => {
      console.log("categories: "+data);
      this.uniqueCategories = data;
    });
  }

  getCampsBySelectedCategories(event: any, category:string){
    if(event.target.checked){
      console.log(category+' Checked');
      this.selectedItems.push(category);

    }else{
      console.log(category+' Unchecked');
      this.selectedItems = this.selectedItems.filter(data => data!=category);
    }
    console.log(this.selectedItems);
    this.getSelectedCampsSortedByPrice();
  }

  getSelectedCampsSortedByPrice(){
    this.campService.getSelectedCampsSortedByPrice(this.selectedItems,this.selectedPriceOption).subscribe(data => {
      console.log("SelectedCampsSortedByPrice: ");
      console.log(data);
      this.camps = data;
    })
  }
  

  private getFavorites(){
    this.favoriteService.viewFavorites(this.userName).subscribe(data => {
        console.log("favorites: "+data);
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




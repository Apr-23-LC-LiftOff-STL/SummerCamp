import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Camp } from '../ModelInterfaces/camp';
import { CampService } from '../Services/camp.service';
import { FavoriteService } from '../Services/favorite.service';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';


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
  userName: string = 'raam';
  gradeGrp: string = '';
  

  constructor(private router: Router, private campService: CampService, private favoriteService: FavoriteService, private toastr: ToastrService, private route: ActivatedRoute ) { }

  ngOnInit(): void {
    this.getCamps();
    this.getUniqueCategories();
    this.getFavorites();
  
  }
  private getCamps() {
    this.route.queryParamMap.subscribe(parms => {
      this.gradeGrp = String(parms.get('gradeGrp'));
      this.campService.getCampsList(this.gradeGrp, this.selectedPriceOption).subscribe(data => {
        this.camps = data;
      }), (error: HttpErrorResponse) => {
        alert(error.message);
      }
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
    this.campService.getSelectedCampsSortedByPrice(this.selectedItems,this.selectedPriceOption, this.gradeGrp).subscribe(data => {
      console.log("SelectedCampsSortedByPrice: ");
      console.log(data);
      this.camps = data;
    })
  }

  private getFavorites() {
    this.favoriteService.viewFavorites(this.userName).subscribe(data => {
      console.log(data);
      this.favoritesList = data;
    });
  }

  addToFavorites(campId: number) {
    console.log(campId)
    this.favoriteService.addToFavorites(campId, this.userName).subscribe(() => {
      this.getFavorites();
      this.toastr.info('marked as my favorite!');
    });
  }

  removeFromFavorites(campId: number) {
    this.favoriteService.removeFromFavorites(campId, this.userName).subscribe(() => {
      this.getFavorites();
      this.toastr.info('removed from my favorites!');
    });
  }

  isFavorite(campId: number): boolean {
    return this.favoritesList.some(camp => camp.id === campId);
  }


  deleteCamp(campId: number) {
    if (confirm("Are you sure you want to delete this camp?")) {
      console.log(campId)
      this.campService.deleteCamp(campId).subscribe(data => {
        // console.log(data);
        // console.log(this.router.url);
        this.router.routeReuseStrategy.shouldReuseRoute = function () {
          return false;
        };

        this.router.navigateByUrl(this.router.url)
        this.toastr.info('Successfully deleted camp');
      });

    }

  }

}




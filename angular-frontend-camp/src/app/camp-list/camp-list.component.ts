import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Camp } from '../ModelInterfaces/camp';
import { CampService } from '../Services/camp.service';
import { FavoriteService } from '../Services/favorite.service';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse, HttpResponse,HttpClient,HttpParams, HttpHeaders} from '@angular/common/http';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';
import { isNull } from '@angular/compiler/src/output/output_ast';
import { isEmpty } from 'rxjs/operators';
import { ColDef } from 'ag-grid-community';



@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {
  camps: Camp[] = [];
  uniqueCategories: string[] = [];
  priceOptions: string[] = ['low to high','high to low'];
  selectedPriceOption: string = this.priceOptions[0];
  selectedItems: string[] = [];
  favoritesList: Camp[] = [];
  userName: string = '';
  gradeGrp: string = '';
  name: string='';
  searchResults: string[] = [];
    currentPage: number = 1;
    itemsPerPage: number = 10; // Number of items per page
  requestHeader: HttpHeaders = new HttpHeaders(
    { "No-Auth":"True"}
  );


  constructor(public userAuthService: UserAuthService, public userService: UserService, 
  private router: Router, private campService: CampService, private favoriteService: FavoriteService, 
  private toastr: ToastrService, private route: ActivatedRoute, private http: HttpClient ) { }



  ngOnInit(): void {
    this.getCamps();
    this.getUniqueCategories();
    this.userName = this.userAuthService.getAccountName();
      if(this.userService.roleMatch(['User'])){
        this.getFavorites();
      }
    }

  private getCamps() {
    this.route.queryParamMap.subscribe(parms => {
      this.gradeGrp = String(parms.get('gradeGrp'));
      this.campService.getCampsList(this.gradeGrp, this.selectedPriceOption).subscribe(data => {
        console.log(data);
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
    console.log(campId);
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

  // Camp Detail display


  public campDetails(id: any){
    console.log(id);
    this.router.navigate(['camp-detail', id]);
  }
  
 

  //Search functionality by name
  
  search() {
  
    console.log(this.name);
    
      //this.http.get('http://localhost:8080/api/camps/?name='+this.name).subscribe((response: any) => {
    return this.http.get<Camp[]>('http://localhost:8080/api/v1/camps/search?name='+this.name, 
    { headers:this.requestHeader}).subscribe((response: any) => {
        console.log(response);
        this.camps = response;
      });
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


  getCampsForPage(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.camps.slice(startIndex, endIndex);
  }

  totalPages(): number {
    return Math.ceil(this.camps.length / this.itemsPerPage);
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
  
	// columnDefs: ColDef[] = [
	// 	{headerName: 'Name', field: 'name' },
	// 	{headerName: 'Category', field: 'category' },
	// 	{headerName: 'Location', field: 'location'},
  //   {headerName: 'Price', field: 'price'}
	// ];

	// rowData = [
	// 	{ make: 'Toyota', model: 'Celica', price: 35000 },
	// 	{ make: 'Ford', model: 'Mondeo', price: 32000 },
	// 	{ make: 'Porsche', model: 'Boxter', price: 72000 }
	// ];

}

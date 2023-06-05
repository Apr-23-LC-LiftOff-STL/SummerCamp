import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Camp } from '../camp';
import { CampService } from '../camp.service';
import { FavoriteService } from '../favorite.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';


@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {

  camps: Camp[] = [];
  favoritesList: Camp[] = [];
  name: string  = '';
  searchResults: string[] | undefined;
  userName = 'raam';


  constructor(private campService: CampService, private favoriteService: FavoriteService, private toastr: ToastrService, private route: ActivatedRoute,private router:Router,private http: HttpClient) { }


  ngOnInit(): void {
    this.getCamps();
    this.getFavorites();
  }

  private getCamps() {
    this.route.queryParamMap.subscribe(parms => {
      const gradeGrp = String(parms.get('gradeGrp'));
      this.campService.getCampsList(gradeGrp).subscribe(data => {
        this.camps = data;
        for (let i = 0; i < this.camps.length; i++) {
          if (this.camps[i].gradeGrp === 'GKTO5') {
            this.camps[i].gradeGrp = 'K-5'
          } else if (this.camps[i].gradeGrp === 'G6TO8') {
            this.camps[i].gradeGrp = '6-8'
          } else if (this.camps[i].gradeGrp === 'G9TO12') {
            this.camps[i].gradeGrp = '9-12'
          }
        }
      });
    })

  }
  public addAsFavorite(campId: number, userId: number) {
    console.log(`Camp id:${campId}`)

  }

  private getFavorites() {
    this.favoriteService.viewFavorites(this.userName).subscribe(data => {
      console.log(data);
      this.favoritesList = data;
    });
  }

  addToFavorites(campId: number) {
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


 public campDetails(id: any){
  console.log(id);
  this.router.navigate(['camp-detail', id]);
}


search() {

  console.log(this.name);

  this.http.get('http://localhost:8080/api/camps?name='+this.name).subscribe((response: any) => {
      this.camps = response;
    });
}

}




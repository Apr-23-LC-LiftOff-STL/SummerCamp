import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Camp } from '../camp';
import { CampService } from '../camp.service';

@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {

  camps: Camp[] = [];

  constructor(
    private campService: CampService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.getCamps();
  }

  private getCamps(){
    this.route.queryParamMap.subscribe(parms => {
      const gradeGrp = String(parms.get('gradeGrp'));
      this.campService.getCampsList(gradeGrp).subscribe(data => {
        this.camps = data;
        for(let i=0; i< this.camps.length; i++){
          if(this.camps[i].gradeGrp === 'GKTO5'){
            this.camps[i].gradeGrp='K-5'
          }else if (this.camps[i].gradeGrp === 'G6TO8'){
            this.camps[i].gradeGrp='6-8'
          }else if (this.camps[i].gradeGrp === 'G9TO12'){
            this.camps[i].gradeGrp='9-12'
          }
        } 
      });
    })
    
  }
  public addAsFavorite(campId:number,userId:number){
    console.log(`Camp id:${campId}`)

  }

}

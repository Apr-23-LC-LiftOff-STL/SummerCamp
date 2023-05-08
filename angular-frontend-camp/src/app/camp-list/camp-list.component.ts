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
    private route: ActivatedRoute) { 
  }

  ngOnInit(): void {
    this.getCamps();
  }

  private getCamps(){
    const gradeGrp = String(this.route.snapshot.paramMap.get('gradeGrp'))
    console.log(gradeGrp)
    this.campService.getCampsList(gradeGrp).subscribe(data => {
        this.camps = data;
    });
  }

}

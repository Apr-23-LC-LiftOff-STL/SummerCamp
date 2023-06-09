import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';

@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {

  camps: Camp[] = [];

  constructor(private campService: CampService) { 
  }

  ngOnInit(): void {
    this.getCamps();
  }

  private getCamps(){
    this.campService.getCampsList().subscribe(data => {
        this.camps = data;
    });
  }

}

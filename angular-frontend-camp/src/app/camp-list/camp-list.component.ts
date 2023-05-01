import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';

@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {
<<<<<<< HEAD
  
  camps: Camp[] = [];
  constructor(private campService: CampService) { }
=======

  camps: Camp[] = [];

  constructor(private campService: CampService) { 
  }
>>>>>>> 35e97f3ab7eaed2768eb1ca27225994cd9786978

  ngOnInit(): void {
    this.getCamps();
  }
<<<<<<< HEAD
  
=======

>>>>>>> 35e97f3ab7eaed2768eb1ca27225994cd9786978
  private getCamps(){
    this.campService.getCampsList().subscribe(data => {
        this.camps = data;
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {

  camps: Camp[] = [];

  constructor(private campService: CampService,private router:Router) { 
  }

  ngOnInit(): void {
    this.getCamps();
  }

  private getCamps(){
    this.campService.getCampsList().subscribe(data => {
      console.log(data);
        this.camps = data;
    });
  }

  
 public campDetails(id: any){
  console.log(id);
  this.router.navigate(['camp-detail', id]);
}

}

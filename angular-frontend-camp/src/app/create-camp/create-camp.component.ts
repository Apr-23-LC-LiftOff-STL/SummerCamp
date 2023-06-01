import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-camp',
  templateUrl: './create-camp.component.html',
  styleUrls: ['./create-camp.component.css']
})
export class CreateCampComponent implements OnInit {
 
  /*camp: Camp = new Camp(0, '', '', 0, '', 0,
  new Date(0), 0, '', '', '');*/
  camp: Camp = new Camp();
  
  constructor(private campService: CampService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveCamp(){
  }

  goToCampList(){
    this.router.navigate(['/camps']);
  }

  onSubmit(){
    console.log(this.camp);
    this.saveCamp();

  }

}

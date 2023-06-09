import { Component, OnInit } from '@angular/core';
import { Camp } from '../ModelInterfaces/camp';
import { CampService } from '../Services/camp.service';
import { Router } from '@angular/router';
import {Location} from '@angular/common';
import { ToastrService } from 'ngx-toastr';

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
    private router: Router, private location: Location,
    private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  saveCamp(){
    this.campService.createCamp(this.camp).subscribe(data => {
       console.log(data);
       this.toastr.info('New Camp created!');
       this.goToCampList();
    },
    error => console.log(error));
  }

  goToCampList(){
      //this.router.navigate(['/camps']);
      this.location.back();
  }
  

  onSubmit(){
    console.log(this.camp);
    this.saveCamp();

  }

}

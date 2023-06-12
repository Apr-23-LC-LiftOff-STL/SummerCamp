import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CampService } from '../Services/camp.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-camp-detail',
  templateUrl: './camp-detail.component.html',
  styleUrls: ['./camp-detail.component.css']
})


export class CampDetailComponent implements OnInit {

   id:any;
   camp: any;

  constructor(private route :ActivatedRoute,private campService : CampService,private location: Location) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];
    
    this.campService.getCamp(this.id).subscribe( data => {
      this.camp = data;
    });

  }

  goBack() {
    console.log("called goBack fn");
    this.location.back();
  }

  reDirect(link:any){
    console.log(link);
    window.open(link);
    // window.location.href="https://www.google.com";
  }

}

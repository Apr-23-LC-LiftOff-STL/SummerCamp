import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { ActivatedRoute } from '@angular/router';
import { CampService } from '../camp.service';

@Component({
  selector: 'app-camp-detail',
  templateUrl: './camp-detail.component.html',
  styleUrls: ['./camp-detail.component.css']
})


export class CampDetailComponent implements OnInit {

   id:any;
   camp: any;

  constructor(private route :ActivatedRoute,private campService : CampService ) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];
    
    this.campService.getCamp(this.id).subscribe( data => {
      this.camp = data;
    });

  }

  reDirect(link:any){
    console.log(link);
    window.open("https://"+link);
    // window.location.href="https://www.google.com";
  }

}

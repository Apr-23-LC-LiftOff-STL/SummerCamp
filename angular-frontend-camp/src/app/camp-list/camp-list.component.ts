import { Component, OnInit } from '@angular/core';
import { Camp } from '../camp';
import { CampService } from '../camp.service';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-camp-list',
  templateUrl: './camp-list.component.html',
  styleUrls: ['./camp-list.component.css']
})
export class CampListComponent implements OnInit {

  camps: Camp[] = [];
  name: string  = '';
  searchResults: string[] | undefined;

  constructor(private campService: CampService,private router:Router,private http: HttpClient) { 
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



search() {

  console.log(this.name);

  this.http.get('http://localhost:8080/api/camps?name='+this.name).subscribe((response: any) => {
      this.camps = response;
    });
}

}

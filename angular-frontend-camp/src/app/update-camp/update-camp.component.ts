import { Component, OnInit } from '@angular/core';
import { Camp } from '../ModelInterfaces/camp';
import { CampService } from '../Services/camp.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-update-camp',
  templateUrl: './update-camp.component.html',
  styleUrls: ['./update-camp.component.css']
})
export class UpdateCampComponent implements OnInit {

  camp: any = new Camp();
  constructor(private campService: CampService, private route: ActivatedRoute,
    private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    let id = this.route.snapshot.params['id'];
    console.log("UpdateComponent Id:" + id)

    this.campService.getCampById(id).subscribe(data => {
      this.camp = data;
    }, error => console.log(error));
  }

  onSubmit() {
    console.log("Update the information : ", this.camp)
    this.campService.updateCamp(this.camp.id, this.camp).subscribe(data => {
      this.toastr.info('Successfully Updated the Camp');
      this.goToCampList(this.camp.gradeGrp);
    }
      , error => {
        this.toastr.error('Failed to update the camp');
        console.error("failed to update camp. Caused by : ", error);
      }
    );
  }

  goToCampList(gradeGrp: String) {
    this.router.navigate(['/camps'], { queryParams: { gradeGrp: gradeGrp } });
  }

}

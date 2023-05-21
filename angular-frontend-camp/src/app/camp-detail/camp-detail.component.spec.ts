import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampDetailComponent } from './camp-detail.component';

describe('CampDetailComponent', () => {
  let component: CampDetailComponent;
  let fixture: ComponentFixture<CampDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCampComponent } from './update-camp.component';

describe('UpdateCampComponent', () => {
  let component: UpdateCampComponent;
  let fixture: ComponentFixture<UpdateCampComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCampComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCampComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampByCategoryComponent } from './camp-by-category.component';

describe('CampByCategoryComponent', () => {
  let component: CampByCategoryComponent;
  let fixture: ComponentFixture<CampByCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampByCategoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CampByCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

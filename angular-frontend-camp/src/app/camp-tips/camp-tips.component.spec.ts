import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampTipsComponent } from './camp-tips.component';

describe('CampTipsComponent', () => {
  let component: CampTipsComponent;
  let fixture: ComponentFixture<CampTipsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampTipsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampTipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

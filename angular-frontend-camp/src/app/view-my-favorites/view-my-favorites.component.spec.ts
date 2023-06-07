import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMyFavoritesComponent } from './view-my-favorites.component';

describe('ViewMyFavoritesComponent', () => {
  let component: ViewMyFavoritesComponent;
  let fixture: ComponentFixture<ViewMyFavoritesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewMyFavoritesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMyFavoritesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

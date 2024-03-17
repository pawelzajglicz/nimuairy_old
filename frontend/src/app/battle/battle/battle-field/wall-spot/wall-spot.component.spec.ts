import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WallSpotComponent } from './wall-spot.component';

describe('WallSpotComponent', () => {
  let component: WallSpotComponent;
  let fixture: ComponentFixture<WallSpotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WallSpotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WallSpotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrbSpotComponent } from './orb-spot.component';

describe('OrbSpotComponent', () => {
  let component: OrbSpotComponent;
  let fixture: ComponentFixture<OrbSpotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrbSpotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrbSpotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

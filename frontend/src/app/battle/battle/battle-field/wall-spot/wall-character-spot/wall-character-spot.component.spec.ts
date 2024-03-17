import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WallCharacterSpotComponent } from './wall-character-spot.component';

describe('WallCharacterSpotComponent', () => {
  let component: WallCharacterSpotComponent;
  let fixture: ComponentFixture<WallCharacterSpotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WallCharacterSpotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WallCharacterSpotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

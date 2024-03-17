import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldPieceComponent } from './field-piece.component';

describe('FieldPieceComponent', () => {
  let component: FieldPieceComponent;
  let fixture: ComponentFixture<FieldPieceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FieldPieceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldPieceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

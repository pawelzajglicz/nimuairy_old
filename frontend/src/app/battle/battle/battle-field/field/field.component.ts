import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, QueryList, ViewChildren } from '@angular/core';
import { FieldCharacter } from 'src/app/battle/models/field-character';
import { FieldPieceComponent } from './field-piece/field-piece.component';

@Component({
  selector: 'nim-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FieldComponent implements AfterViewInit {

  @Input() leftPlayerCharacters: Set<FieldCharacter>;
  @Input() rightPlayerCharacters: Set<FieldCharacter>;

  @Input() set xSize(value: number) {
    this._xSize = value;
    this.dummyXArray = [].constructor(value);
  }

  @Input() set ySize(value: number) {
    this._ySize = value;
    this.dummyYArray = [].constructor(value);
  }

  _xSize: number;
  dummyXArray: number[];
  get xSize(): number {
    return this._xSize;
  }

  _ySize: number;
  dummyYArray: number[];
  get ySize(): number {
    return this._xSize;
  }

  @ViewChildren(FieldPieceComponent) fieldPieces: QueryList<FieldPieceComponent>;

  constructor(private changeDetector: ChangeDetectorRef) { }

  ngAfterViewInit() {
    setTimeout(() => {
      this.placeCharacters(this.leftPlayerCharacters);
      this.placeCharacters(this.rightPlayerCharacters);
      this.changeDetector.markForCheck();
    })
  }

  placeCharacters(playerCharacters: Set<FieldCharacter>) {
    playerCharacters.forEach(character => this.placeCharacter(character));
  }

  placeCharacter(character: FieldCharacter) {
    this.fieldPieces.forEach(fieldPiece => {
      if (character.xPosition === fieldPiece.x && character.yPosition === fieldPiece.y) {
        fieldPiece.setCharacter(character);
        return;
      }
    })
  }
}

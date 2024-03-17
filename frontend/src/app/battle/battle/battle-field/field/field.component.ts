import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Character, CharacterActionState } from 'src/app/battle/models/character';
import { FieldCharacter, isFieldCharacter } from 'src/app/battle/models/field-character';
import { BattleService } from '../../battle.service';
import { FieldPieceComponent } from './field-piece/field-piece.component';

@Component({
  selector: 'nim-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FieldComponent implements AfterViewInit, OnInit {

  @Input() leftPlayerCharacters: FieldCharacter[];
  @Input() rightPlayerCharacters: FieldCharacter[];

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

  public highlightedMoveFieldsArray: [number, number][] = [];
  public highlightedAttackFieldsArray: [number, number][] = [];

  @ViewChildren(FieldPieceComponent) fieldPieces: QueryList<FieldPieceComponent>;

  constructor(private battleService: BattleService,
              private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.battleService.characterSelected$.subscribe(character => this.onCharacterSelect(character));
    this.battleService.characterMoved$.subscribe(characterMove => this.onCharacterMove(characterMove));
    this.battleService.turnEnded$.subscribe(() => this.onTurnEnd());
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.placeCharacters(this.leftPlayerCharacters);
      this.placeCharacters(this.rightPlayerCharacters);
      this.changeDetector.markForCheck();
    })
  }

  placeCharacters(playerCharacters: FieldCharacter[]) {
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

  onCharacterSelect(character: Character): void {
    this.highlightFields(character);
  }

  onCharacterMove(characterMove: { character: FieldCharacter; newX: number; newY: number; }): void {
    this.clearField(characterMove.character.xPosition, characterMove.character.yPosition);
    characterMove.character.xPosition = characterMove.newX;
    characterMove.character.yPosition = characterMove.newY;
    this.placeCharacter(characterMove.character);
    this.highlightFields(characterMove.character);
  }

  onTurnEnd() {
    this.highlightedAttackFieldsArray = [];
    this.highlightedMoveFieldsArray = [];
    this.changeDetector.markForCheck();
  }

  clearField(xPosition: number, yPosition: number) {
    this.fieldPieces.forEach(fieldPiece => {
      if (xPosition === fieldPiece.x && yPosition === fieldPiece.y) {
        fieldPiece.setCharacter(null);
        return;
      }
    })
  }

  public shouldBeHighlightedAsAttack(xPosition: number, yPosition: number): boolean {
    return !!this.highlightedAttackFieldsArray.find(el => el[0] === xPosition && el[1] === yPosition);
  }

  public shouldBeHighlightedAsMove(xPosition: number, yPosition: number): boolean {
    return !!this.highlightedMoveFieldsArray.find(el => el[0] === xPosition && el[1] === yPosition);
  }

  private highlightAttackFields(character: FieldCharacter): void {
    let xPosition, yPosition;
    for (let i = character.attackRange * (-1); i < character.attackRange + 1; i++) {
      for (let j = character.attackRange * (-1); j < character.attackRange + 1; j++) {
        xPosition = character.xPosition + i;
        yPosition = character.yPosition + j;
        if (this.coordinatesWithinBattleField(xPosition, yPosition)) {
          this.highlightedAttackFieldsArray.push([xPosition, yPosition]);
        }
      }
    }
  }

  private highlightMoveFields(character: FieldCharacter): void {
    let xPosition, yPosition;
    for (let i = character.moveRange * (-1); i < character.moveRange + 1; i++) {
      for (let j = character.moveRange * (-1); j < character.moveRange + 1; j++) {
        xPosition = character.xPosition + i;
        yPosition = character.yPosition + j;
        if (this.coordinatesWithinBattleField(xPosition, yPosition)) {
          this.highlightedMoveFieldsArray.push([xPosition, yPosition]);
        }
      }
    }
  }

  private coordinatesWithinBattleField(xPosition: number, yPosition: number): boolean {
    if (xPosition < 0) {
      return false;
    }

    if (yPosition < 0) {
      return false;
    }

    if (xPosition > this.xSize) {
      return false;
    }

    if (yPosition > this.ySize) {
      return false;
    }

    return true;
  }

  private highlightFields(character: Character): void {
    this.highlightedAttackFieldsArray = [];
    this.highlightedMoveFieldsArray = [];
    if (isFieldCharacter(character)) {
      if (character.state === CharacterActionState.MOVE) {
        this.highlightMoveFields(character);
      } else if (character.state === CharacterActionState.ATTACK) {
        this.highlightAttackFields(character);
      }
    }
  }
}

import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { BattleService } from './battle.service';
import { Battle } from '../models/battle';
import { Character, CharacterActionState } from '../models/character';
import { Player } from '../models/player';
import { FieldCharacter, isFieldCharacter } from '../models/field-character';
import { BattleFieldComponent } from './battle-field/battle-field.component';
import { PlayerSide } from './enums/player-side';

@Component({
  selector: 'nim-battle',
  templateUrl: './battle.component.html',
  styleUrls: ['./battle.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BattleComponent implements OnInit {

  @ViewChild(BattleFieldComponent) battleField: BattleFieldComponent;

  constructor(private battleService: BattleService,
              private changeDetector: ChangeDetectorRef) { }

  battle: Battle;
  public turnPlayer: Player;

  ngOnInit() {
    this.battleService.getBasicBattle()
      .subscribe(battle => {
        this.battle = battle;
        this.changeDetector.markForCheck();
        this.turnPlayer = battle.leftPlayer;
        this.getAllFieldCharacters().forEach((fieldCharacter: Character) => {
          fieldCharacter.state = CharacterActionState.NONE;
        })
      });

      this.battleService.characterClicked$.subscribe(character => this.handleCharacterClicked(character));
      this.battleService.fieldClicked$.subscribe(field => this.handleFieldClicked(field));
      this.battleService.wallClicked$.subscribe(wallCharacter => this.handleWallCharacterClicked(wallCharacter));
      this.battleService.orbClicked$.subscribe(orbCharacter => this.handleOrbCharacterClicked(orbCharacter));
      this.battleService.winnerPlayer$.subscribe((winnerPlayerSide: PlayerSide) => this.handleWinnerPlayer(winnerPlayerSide));
  }

  handleCharacterClicked(clickedCharacter: Character): void {

    const characterSelected = this.getSelectedCharacter();
    if (!characterSelected || (!!characterSelected && characterSelected.state !== CharacterActionState.ATTACK)) {
      this.getAllFieldCharacters().forEach((fieldCharacter: Character) => {
        if (fieldCharacter === clickedCharacter) {
          if (clickedCharacter.state === CharacterActionState.MOVE) {
            clickedCharacter.state = CharacterActionState.ATTACK;
          } else if (!clickedCharacter.movedInCurrentTurn) {
            clickedCharacter.state = CharacterActionState.MOVE;
          }
        } else {
          fieldCharacter.state = CharacterActionState.NONE;
        }
      });
    } else if (characterSelected?.state === CharacterActionState.ATTACK && clickedCharacter !== characterSelected && characterSelected.side === this.turnPlayer.side) {
      if (this.clickedCharacterIsInAttackRange(characterSelected as FieldCharacter, clickedCharacter as FieldCharacter)) {
        this.attack(characterSelected, clickedCharacter);
        this.endTurn();
        return;
      }
    } else if (characterSelected.state === CharacterActionState.ATTACK && characterSelected === clickedCharacter && !characterSelected.movedInCurrentTurn) {
      characterSelected.state = CharacterActionState.MOVE;
    }

    if (isFieldCharacter(clickedCharacter)) {
          this.highlightPossibleMoveFields(clickedCharacter);
          this.battleService.handleCharacterSelect(clickedCharacter);
    }
  }

  highlightPossibleMoveFields(character: FieldCharacter) {
    console.log('highlightPossibleMoveFields', character);
  }

  public handleFieldClicked(coordinates: {x: number, y: number}): void {
    const characterSelected = this.getSelectedCharacter();

    if (!characterSelected) {
      return;
    }

    if (characterSelected.side !== this.turnPlayer.side) {
      return;
    }

    if (isFieldCharacter(characterSelected)) {
      if (characterSelected.state === CharacterActionState.MOVE && this.isFieldInMoveRange(characterSelected, coordinates)) {
        characterSelected.movedInCurrentTurn = true;
        characterSelected.state = CharacterActionState.ATTACK;
        this.battleService.handleCharacterMoved(characterSelected, coordinates.x, coordinates.y);
        this.changeDetector.markForCheck();
        return;
      }
    }

    if (characterSelected.state === CharacterActionState.ATTACK) {
      const characterInClickedField = this.getAllFieldCharacters()
        .filter(character => isFieldCharacter(character))
        .map(character => character as FieldCharacter)
        .find(character => character.xPosition === coordinates.x && character.yPosition === coordinates.y);

        if (!!characterInClickedField) {
          this.attack(characterSelected, characterInClickedField);
        }

        this.endTurn();
        this.changeDetector.markForCheck();
    }
  }

  attack(characterSelected: Character, characterInClickedField: Character): void {
    characterInClickedField.currentHealth -= characterSelected.attackPower;
    if (characterInClickedField.currentHealth <= 0) {
      this.handleFieldCharacterDeath(characterInClickedField as FieldCharacter);
    }
  }

  clickedCharacterIsInAttackRange(characterSelected: FieldCharacter, character: FieldCharacter): boolean {
    return (Math.abs(characterSelected.xPosition - character.xPosition) <= characterSelected.attackRange)
        && (Math.abs(characterSelected.yPosition - character.yPosition) <= characterSelected.attackRange);
  }

  handleWallCharacterClicked(wallCharacter: Character): void {
    const characterSelected = this.getSelectedCharacter();

    if (!characterSelected) {
      return;
    }

    if (characterSelected.state === CharacterActionState.ATTACK && this.isInRangeAttack()) {
      this.attack(characterSelected, wallCharacter);
      this.endTurn();
      this.changeDetector.markForCheck();
    }
  }

  public isInRangeAttack(): boolean {
    // TODO
    return true;
  }

  handleOrbCharacterClicked(orbCharacter: Character): void {
    const characterSelected = this.getSelectedCharacter();

    if (!characterSelected) {
      return;
    }

    if (characterSelected.state === CharacterActionState.ATTACK && this.isInRangeAttack()) {
      this.attack(characterSelected, orbCharacter);
      this.endTurn();
      this.changeDetector.markForCheck();
    }
  }

  public endTurn(): void {
    if (this.turnPlayer.side === PlayerSide.LEFT) {
      this.turnPlayer = this.battle.rightPlayer;
    } else {
      this.turnPlayer = this.battle.leftPlayer;
    }
    this.getAllFieldCharacters().forEach(character => {
      character.state = CharacterActionState.NONE;
      character.movedInCurrentTurn = false;
    });
    this.battleService.handleTurnEnded();
    this.changeDetector.markForCheck();
  }

  private getSelectedCharacter(): Character {
    return this.getAllFieldCharacters().find(character => character.state !== CharacterActionState.NONE) as Character;
  }

  isFieldInMoveRange(characterSelected: FieldCharacter, coordinates: { x: number; y: number; }): boolean {
    return (Math.abs(characterSelected.xPosition - coordinates.x) <= characterSelected.moveRange)
        && (Math.abs(characterSelected.yPosition - coordinates.y) <= characterSelected.moveRange);
  }

  private getAllFieldCharacters(): Character[] {
    return [
      ...this.battle.leftPlayer.fieldCharacters,
      ...this.battle.rightPlayer.fieldCharacters
    ];
  }

  private handleFieldCharacterDeath(deadCharacter: FieldCharacter): void {
    const characterOwner = deadCharacter.side === PlayerSide.LEFT ? this.battle.leftPlayer : this.battle.rightPlayer;
    const characterIndex = characterOwner.fieldCharacters.indexOf(deadCharacter);
    characterOwner.fieldCharacters.splice(characterIndex, 1);
    this.battleService.handleCharacterDeath(deadCharacter);
  }

  handleWinnerPlayer(winnerPlayerSide: PlayerSide): void {
    if (winnerPlayerSide == PlayerSide.LEFT) {
      window.alert(this.battle.leftPlayer.name + ' won!')
    } else {
      window.alert(this.battle.rightPlayer.name + ' won!')
    }
  }
}

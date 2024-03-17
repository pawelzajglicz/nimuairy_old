import { ChangeDetectionStrategy, ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PlayerSide } from '../enums/player-side';
import { Character, CharacterActionState } from '../../models/character';
import { BattleService } from '../battle.service';

@Component({
  selector: 'nim-character',
  templateUrl: './character.component.html',
  styleUrls: ['./character.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CharacterComponent implements OnInit {

  @Input() character: Character;
  @Input() imageUrl: string;
  @Input() stopClick: boolean = true;
  @Output() characterClicked: EventEmitter<Character> = new EventEmitter();;

  CharacterActionState = CharacterActionState;
  playerSide = PlayerSide;

  constructor(private battleService: BattleService,
              private changeDetector: ChangeDetectorRef) {}

              ngOnInit(): void {
                this.battleService.characterClicked$.subscribe(() => this.changeDetector.markForCheck());
                this.battleService.turnEnded$.subscribe(() => this.changeDetector.markForCheck());
                // this.battleService.characterDead$.subscribe(character => {
                //   if (character === this.character) {
                //     this.destroy();
                //   }
                // });
              }

  public onClick(event: any): void {
    this.characterClicked.emit(this.character);
    this.battleService.handleCharacterClick(this.character);
    this.changeDetector.markForCheck();
    if (this.stopClick) {
      event.stopImmediatePropagation();
    }
  }

}

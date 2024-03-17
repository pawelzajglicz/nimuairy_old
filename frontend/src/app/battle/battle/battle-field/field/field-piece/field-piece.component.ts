import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { FieldCharacter } from 'src/app/battle/models/field-character';
import { BattleService } from '../../../battle.service';

@Component({
  selector: 'nim-field-piece',
  templateUrl: './field-piece.component.html',
  styleUrls: ['./field-piece.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FieldPieceComponent implements OnInit {

  @Input() x: number;
  @Input() y: number;
  @Input() highlightedMove: boolean;
  @Input() highlightedAttack: boolean;

  character: FieldCharacter;

  constructor(private battleService: BattleService,
              private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.battleService.characterClicked$.subscribe(() => this.changeDetector.markForCheck());
    this.battleService.characterDead$.subscribe(character => {
      if (character === this.character) {
        this.character = null;
      }
    });
  }

  setCharacter(character: FieldCharacter) {
    this.character = character;
    this.changeDetector.markForCheck();
  }

  public onClick(): void {
    this.battleService.handleFieldClicked(this.x, this.y);
  }
}

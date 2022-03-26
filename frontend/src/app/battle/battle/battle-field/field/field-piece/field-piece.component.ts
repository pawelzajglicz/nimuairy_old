import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input } from '@angular/core';
import { FieldCharacter } from 'src/app/battle/models/field-character';

@Component({
  selector: 'nim-field-piece',
  templateUrl: './field-piece.component.html',
  styleUrls: ['./field-piece.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FieldPieceComponent {

  @Input() x: number;
  @Input() y: number;

  character: FieldCharacter;

  constructor(private changeDetector: ChangeDetectorRef) { }

  setCharacter(character: FieldCharacter) {
    this.character = character;
    this.changeDetector.markForCheck();
  }
}

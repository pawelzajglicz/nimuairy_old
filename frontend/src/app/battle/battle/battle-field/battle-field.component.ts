import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { Battle } from '../../models/battle';

@Component({
  selector: 'nim-battle-field',
  templateUrl: './battle-field.component.html',
  styleUrls: ['./battle-field.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BattleFieldComponent {

  @Input() battle: Battle;

  constructor() { }


}


import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { Orb } from 'src/app/battle/models/orb';

@Component({
  selector: 'nim-orb-spot',
  templateUrl: './orb-spot.component.html',
  styleUrls: ['./orb-spot.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OrbSpotComponent {

  @Input() orb: Orb;

  constructor() { }
}

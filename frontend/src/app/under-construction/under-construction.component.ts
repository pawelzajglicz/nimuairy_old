import {trigger, transition, style, animate, state} from '@angular/animations';
import {Component} from '@angular/core';

@Component({
  selector: 'nim-under-construction',
  templateUrl: './under-construction.component.html',
  styleUrls: ['./under-construction.component.scss'],
  animations: [
        trigger('hideButton', [
          state('hide', style({
            height: 0,
            'border-style': 'unset',
            'font-size': 0,
            'padding-top': 0,
            'padding-bottom': 0
          }))
        ]),
        trigger('heightHide', [
            state('hide', style({
                height: 0,
                'padding-top': 0,
                'padding-bottom': 0
            })),
            transition('* => *', animate(750))
        ])
    ]
})
export class UnderConstructionComponent {

  state = 'visible';

  changeVisibility(): void {
    (this.state === 'hide') ? this.state = 'visible' : this.state = 'hide';
  }

  constructor() { }

}

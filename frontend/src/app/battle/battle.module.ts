import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { BattleRoutingModule } from './battle-routing.module';
import { BattleComponent } from './battle/battle.component';
import { BattleFieldComponent } from './battle/battle-field/battle-field.component';
import { FieldPieceComponent } from './battle/battle-field/field/field-piece/field-piece.component';
import { OrbSpotComponent } from './battle/battle-field/orb-spot/orb-spot.component';
import { WallSpotComponent } from './battle/battle-field/wall-spot/wall-spot.component';
import { WallCharacterSpotComponent } from './battle/battle-field/wall-spot/wall-character-spot/wall-character-spot.component';
import { FieldComponent } from './battle/battle-field/field/field.component';
import { CharacterComponent } from './battle/character/character.component';



@NgModule({
  declarations: [
    BattleComponent,
    BattleFieldComponent,
    CharacterComponent,
    FieldComponent,
    FieldPieceComponent,
    OrbSpotComponent,
    WallCharacterSpotComponent,
    WallSpotComponent
  ],
  imports: [
    BattleRoutingModule,
    CommonModule,
    MatGridListModule
  ],
  bootstrap: [BattleComponent]
})
export class BattleModule { }

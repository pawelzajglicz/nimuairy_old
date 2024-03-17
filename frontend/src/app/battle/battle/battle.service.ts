import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable, Subject, tap } from 'rxjs';

import { Battle } from '../models/battle';
import { environment } from '../../../environments/environment';
import { Character } from '../models/character';
import { PlayerSide } from './enums/player-side';
import { FieldCharacter } from '../models/field-character';
import { WallCharacter } from '../models/wall-character';
import { Orb } from '../models/orb';

@Injectable({
  providedIn: 'root'
})
export class BattleService {

  private battle: Battle;

  private characterClicked = new Subject<Character>();
  public characterClicked$ = this.characterClicked.asObservable();

  private characterSelected = new Subject<Character>();
  public characterSelected$ = this.characterSelected.asObservable();

  private fieldClicked = new Subject<{x: number, y: number}>();
  public fieldClicked$ = this.fieldClicked.asObservable();

  private characterMoved = new Subject<{character: FieldCharacter, newX: number, newY: number}>();
  public characterMoved$ = this.characterMoved.asObservable();

  private characterDead = new Subject<Character>();
  public characterDead$ = this.characterDead.asObservable();

  private wallClicked = new Subject<WallCharacter>();
  public wallClicked$ = this.wallClicked.asObservable();

  private orbClicked = new Subject<Orb>();
  public orbClicked$ = this.orbClicked.asObservable();

  private turnEnded = new Subject<void>();
  public turnEnded$ = this.turnEnded.asObservable();

  private winnerPlayer = new Subject<PlayerSide>();
  public winnerPlayer$ = this.winnerPlayer.asObservable();

  constructor(private http: HttpClient) { }

  getBasicBattle(): Observable<Battle> {
    return this.http.get<Battle>(environment.apiUrl + 'battles/basic')
      .pipe(
        tap((battle: Battle) => {
          this.battle = battle;
          this.battle.leftPlayer.side = PlayerSide.LEFT;
          this.battle.rightPlayer.side = PlayerSide.RIGHT;
        })
      );
  }

  public handleCharacterClick(character: Character): void {
      this.characterClicked.next(character);
  };

  public handleCharacterSelect(character: Character): void {
      this.characterSelected.next(character);
  };

  public handleFieldClicked(x: number, y: number): void {
    this.fieldClicked.next({x, y});
  }

  public handleCharacterMoved(character: FieldCharacter, newX: number, newY: number): void {
    this.characterMoved.next({character, newX, newY});
  }

  public handleWallClicked(wallCharacter: WallCharacter): void {
    this.wallClicked.next(wallCharacter);
  }

  public handleOrbClicked(orbCharacter: Orb): void {
    this.orbClicked.next(orbCharacter);
  }

  public handleTurnEnded(): void {
    this.turnEnded.next();
  }

  public handleCharacterDeath(deadCharacter: FieldCharacter) {
    this.characterDead.next(deadCharacter);
  }

  public announcePlayerWin(playerSide: PlayerSide) {
    this.winnerPlayer.next(playerSide);
  }
}

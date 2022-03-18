import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Battle } from '../models/battle';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BattleService {

  constructor(private http: HttpClient) { }

  getBasicBattle(): Observable<Battle> {
    return this.http.get<Battle>(environment.apiUrl + 'battles/basic');
  }
}

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { ChatComponent } from './chat/chat.component';
import {NotFoundComponent} from './errors/not-found/not-found.component';
import {ServerErrorComponent} from './errors/server-error/server-error.component';
import {HomeComponent} from './home/home.component';
import { RegisterComponent } from './register/register.component';

//const TITLE_BASE = 'Nimuairy';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'friends', component: ChatComponent },
 // { path: 'friends', component: ChatComponent, title: TITLE_BASE + ' - friends'}, uncomment after upgrade to v14
  { path: 'register', component: RegisterComponent },
  { path: 'not-found', component: NotFoundComponent },
  { path: 'server-error', component: ServerErrorComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

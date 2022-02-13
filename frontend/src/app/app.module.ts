import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';
import { ContactsListComponent } from './chat/contacts-list/contacts-list.component';
import { ConversationInputComponent } from './chat/conversation-window/conversation-input/conversation-input.component';
import { ConversationWindowComponent } from './chat/conversation-window/conversation-window.component';
import { ExtractOtherNamesPipe } from './chat/conversation-window/extract-other-names.pipe';
import { MessagesListComponent } from './chat/conversation-window/messages-list/messages-list.component';
import { AppRoutingModule } from './app-routing.module';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { ServerErrorComponent } from './errors/server-error/server-error.component';
import { HomeComponent } from './home/home.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { NotificationModule } from './notification-module/notification.module';
import { RegisterComponent } from './register/register.component';
import { UnderConstructionComponent } from './under-construction/under-construction.component';
import { UpperPanelComponent } from './upper-panel/upper-panel.component';

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    ContactsListComponent,
    ConversationInputComponent,
    ConversationWindowComponent,
    ExtractOtherNamesPipe,
    HomeComponent,
    LoginComponent,
    MessagesListComponent,
    NavigationComponent,
    NotFoundComponent,
    RegisterComponent,
    ServerErrorComponent,
    UnderConstructionComponent,
    UpperPanelComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    NotificationModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

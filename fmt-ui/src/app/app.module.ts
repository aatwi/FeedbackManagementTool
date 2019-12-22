import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from './components/home/home.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {LoginSuccessComponent} from './components/login-status/login-success.component';
import {CreateUserComponent} from "./components/create-user/create-user.component";
import {CreateUserSuccessComponent} from './components/create-user-success/create-user-success.component';
import {DataTransferService} from "./services/data-transfer.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateUserComponent,
    HomeComponent,
    LoginSuccessComponent,
    CreateUserSuccessComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [
    DataTransferService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

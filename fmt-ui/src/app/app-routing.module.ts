import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginSuccessComponent} from "./components/login-status/login-success.component";
import {CreateUserComponent} from "./components/create-user/create-user.component";
import {CreateUserSuccessComponent} from "./components/create-user-success/create-user-success.component";


const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "loginSuccess", component: LoginSuccessComponent},
  {path: "createUser", component: CreateUserComponent},
  {path: "createUserSuccess", component: CreateUserSuccessComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./components/user-access/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {CreateUserComponent} from "./components/user-access/create-user/create-user.component";
import {UserHomePage} from "./components/feedback/user-home-page/user-home-page.component";


const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "loginSuccess", component: UserHomePage},
  {path: "createUser", component: CreateUserComponent},
  {path: "createUserSuccess", component: UserHomePage}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

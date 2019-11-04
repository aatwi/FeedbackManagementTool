import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../domain/user";
import {LoginUrlBuilder} from "./login-url-builder";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) {
  }

  login(user: string, password: string): Observable<User> {
    let url = LoginUrlBuilder.logInUrl().withUser(user).withPassword(password).build();
    return this.httpClient.get<User>(url);
  }

}

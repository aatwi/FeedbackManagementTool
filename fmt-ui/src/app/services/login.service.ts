import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../domain/user";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) {
  }

  login(user: string, password: string): Observable<User> {
    let url = 'http://localhost:4200/api/login/' + user + '/' + password;
    return this.httpClient.get<User>(url);
  }

}

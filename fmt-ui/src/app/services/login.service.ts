import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../domain/user";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) {
  }

  login(user: string, password: string): Observable<User> {
    let url = 'https://localhost:8080/api/v1/login/' + user + '/' + password;
    return this.httpClient.get<User>(url);
  }

}

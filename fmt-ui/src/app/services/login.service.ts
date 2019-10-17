import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) {
  }

  login(user: string, password: string) {
    let url = 'http://localhost:8080/api/v1/login/' + user + '/' + password;
    return this.httpClient.get(url);
  }

}

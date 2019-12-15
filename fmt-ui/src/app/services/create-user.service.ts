import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../domain/user";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CreateUserService {
  private service = 'users/create/';

  constructor(private httpClient: HttpClient) {
  }

  createUser(user: User): Observable<User> {
    const url = environment.restApiUrl + this.service;

    return this.httpClient.post<User>(url, user);
  }

}

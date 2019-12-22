import {Injectable} from "@angular/core";
import {User} from "../domain/user";
import {BehaviorSubject} from "rxjs";

@Injectable()
export class DataTransferService {

  private userBehaviorSubject: BehaviorSubject<User> = new BehaviorSubject(null);
  loggedInUser = this.userBehaviorSubject.asObservable();

  constructor() {
  }

  setLoggedInUser(user: User) {
    this.userBehaviorSubject.next(user);
  }
}

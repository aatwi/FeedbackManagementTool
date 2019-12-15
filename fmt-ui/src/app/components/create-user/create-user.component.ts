import {Component, OnInit} from '@angular/core';
import {InputValidator} from "../../helpers/input-validator";
import {CreateUserService} from "../../services/create-user.service";
import {User} from "../../domain/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent implements OnInit {
  router: Router;
  userEmail: string;
  userName: string;
  password: string;
  passwordReEntered: string;
  createUserButtonClicked: boolean;
  errorMessage: string;
  createdUser: User;

  createUserService: CreateUserService;

  constructor(private createUserSrv: CreateUserService, private routerParam: Router) {
    this.createUserService = createUserSrv;
    this.router = routerParam;
  }

  ngOnInit() {
  }

  createUser() {
    this.createUserButtonClicked = true;
    let newUser = new User(this.userName, this.userEmail, this.password);
    this.createUserService.createUser(newUser).subscribe(createdUser => {
        this.createdUser = createdUser;
      },
      error1 => {
        console.log(error1);
      }, () =>
        this.router.navigate(['createUserSuccess']));
  }

  inputIsInvalid(): boolean {
    if (this.createUserButtonClicked) {
      if (!InputValidator.isEmailValid(this.userEmail)) {
        this.errorMessage = '*Please enter a valid email!';
        return true;
      }
      if (!InputValidator.isValidString(this.userName)) {
        this.errorMessage = '*Please enter a valid name!';
        return true;
      }
      if (!InputValidator.isValidString(this.password)) {
        this.errorMessage = '*Please enter a valid password!';
        return true;
      }
      if (CreateUserComponent.passwordDontMatch(this.password, this.passwordReEntered)) {
        this.errorMessage = '*Passwords do not match!';
        return true;
      }
    }
    return false;
  }

  private static passwordDontMatch(password1: string, password2: string) {
    return password1 != password2;
  }
}

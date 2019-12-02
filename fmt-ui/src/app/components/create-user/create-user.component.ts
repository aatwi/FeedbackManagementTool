import {Component, OnInit} from '@angular/core';
import {InputValidator} from "../../helpers/input-validator";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent implements OnInit {
  userEmail: string;
  userName: string;
  password: string;
  passwordReEntered: string;
  createUserButtonClicked: boolean;
  errorMessage: string;

  constructor() {
  }

  ngOnInit() {
  }

  createUser() {
    this.createUserButtonClicked = true;
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
      if (this.passwordDontMatch(this.password, this.passwordReEntered)) {
        this.errorMessage = '*Passwords do not match!';
        return true;
      }
    }
    return false;
  }

  private passwordDontMatch(password1: string, password2: string) {
    return password1 != password2;
  }
}

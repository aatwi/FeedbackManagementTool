import {Component, OnInit} from '@angular/core';
import {HashValueGenerator} from "../helpers/hash-value-generator";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  userEmail: string;
  userPassword: string;
  errorMessage: string;
  loginButtonClicked: boolean;

  constructor() {
  }

  ngOnInit() {
  }

  login() {
    this.loginButtonClicked = true;
    var password = HashValueGenerator.getHashFor(this.userPassword)
    // login(this.userEmail, password);
  }

  invalidInput(): boolean {

    if (this.loginButtonClicked) {
      if (this.emailIsNotValid(this.userEmail)) {
        return true;
      }
      if (this.userPassword == undefined || this.userPassword.length == 0) {
        this.errorMessage = '* Please enter your password';
        return true;
      }
    }
    return false;
  }

  emailIsNotValid(userEmail: string): boolean {
    const emailRegEx = new RegExp('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9]+$');
    return userEmail == undefined || userEmail.length == 0 || !emailRegEx.test(this.userEmail);
  }
}

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
      const regExp = new RegExp('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$');
      const validEmail = regExp.test(this.userEmail);
      if (!validEmail || this.userEmail == undefined || this.userEmail.length == 0) {
        this.errorMessage = '* Please enter a valid email address';
        return true;
      }
      if (this.userPassword == undefined || this.userPassword.length == 0) {
        this.errorMessage = '* Please enter your password';
        return true;
      }
    }
    return false;
  }
}

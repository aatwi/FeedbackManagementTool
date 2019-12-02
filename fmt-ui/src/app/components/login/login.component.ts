import {Component, OnInit} from '@angular/core';
import {HashValueGenerator} from "../../helpers/hash-value-generator";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {InputValidator} from "../../helpers/input-validator";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  router: Router;
  userEmail: string;
  userPassword: string;
  errorMessage: string;
  loginButtonClicked: boolean;
  failedPassword: boolean;
  loggedInUser;

  loginService: LoginService;


  constructor(private serviceLogin: LoginService,
              private routerParam: Router) {
    this.router = routerParam;
    this.loginService = serviceLogin;
  }

  ngOnInit() {
  }

  login() {
    this.loginButtonClicked = true;
    this.loginService.login(this.userEmail, HashValueGenerator.getHashFor(this.userPassword))
      .subscribe(data => {
          this.loggedInUser = data
        },
        error1 => {
          this.failedPassword = true;
          this.inputIsInvalid();
        },
        () => this.router.navigate(['loginSuccess']));
  }

  inputIsInvalid(): boolean {
    if (this.loginButtonClicked) {
      if (this.failedPassword) {
        this.errorMessage = '*Wrong Username or Password!';
        return true;
      }
      if (!InputValidator.isEmailValid(this.userEmail)) {
        this.errorMessage = '*Please enter a valid email!';
        return true;
      }
      if (!InputValidator.isValidString(this.userPassword)) {
        this.errorMessage = '*Please enter your password!';
        return true;
      }
    }
    return false;
  }
}

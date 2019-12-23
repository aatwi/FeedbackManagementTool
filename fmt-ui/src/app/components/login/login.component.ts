import {Component, OnInit} from '@angular/core';
import {HashValueGenerator} from "../../helpers/hash-value-generator";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {InputValidator} from "../../helpers/input-validator";
import {User} from "../../domain/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  userEmail: string;
  userPassword: string;
  errorMessage: string;
  loginButtonClicked: boolean;
  failedPassword: boolean;
  loggedInUser: User;

  loginService: LoginService;
  router: Router;


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
          console.log(error1);
          this.inputIsInvalid();
        },
        () => {
          this.router.navigate(['loginSuccess']);
        });
  }

  inputIsInvalid(): boolean {
    if (this.loginButtonClicked) {
      if (this.failedPassword) {
        this.errorMessage = '*Wrong Username or Password!';
        return true;
      } else {
        let errorMsg: string;
        errorMsg = InputValidator.validateLoginInput(this.userEmail, this.userPassword);
        if (errorMsg) {
          this.errorMessage = errorMsg;
          return true;
        }
      }
    }
    return false;
  }
}

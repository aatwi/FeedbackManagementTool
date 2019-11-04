import {Component, OnInit} from '@angular/core';
import {HashValueGenerator} from "../../helpers/hash-value-generator";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

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
        error1 => console.error(error1),
        () => this.router.navigate(['loginSuccess']));
  }

  invalidInput(): boolean {
    if (this.loginButtonClicked) {
      if (this.emailIsNotValid(this.userEmail)) {
        this.errorMessage = '*Please enter a valid email!';
        return true;
      }
      if (this.passwordIsNotValid(this.userPassword)) {
        this.errorMessage = '*Please enter your password!';
        return true;
      }
    }
    return false;
  }

  emailIsNotValid(userEmail: string): boolean {
    const emailRegEx = new RegExp('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9]+$');
    return userEmail == undefined || userEmail.length == 0 || !emailRegEx.test(this.userEmail);
  }

  passwordIsNotValid(password: string): boolean {
    return password == undefined || password.length == 0;
  }
}

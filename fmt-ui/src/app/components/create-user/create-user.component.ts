import {Component, OnInit} from '@angular/core';

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

  invalidInput(): boolean {
    if (this.createUserButtonClicked) {
      if (this.emailIsNotValid(this.userEmail)) {
        this.errorMessage = '*Please enter a valid email!';
        return true;
      }
      if (this.userNameIsNotValid(this.userName)) {
        this.errorMessage = '*Please enter a valid name!';
        return true;
      }
      if (this.passwordIsNotValid(this.password)) {
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

  emailIsNotValid(userEmail: string): boolean {
    const emailRegEx = new RegExp('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.][a-zA-Z0-9]+$');
    return userEmail == undefined || userEmail.length == 0 || !emailRegEx.test(this.userEmail);
  }

  userNameIsNotValid(name: string) {
    return name == undefined || name.length == 0;
  }

  private passwordIsNotValid(password: string) {
    return password == undefined || password.length == 0;
  }

  private passwordDontMatch(password1: string, password2: string) {
    return password1 != password2;
  }
}

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

  constructor() {
  }

  ngOnInit() {
  }

  createUser() {

  }
}

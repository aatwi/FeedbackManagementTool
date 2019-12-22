import {Component, OnInit} from '@angular/core';
import {InputValidator} from "../../helpers/input-validator";
import {CreateUserService} from "../../services/create-user.service";
import {User} from "../../domain/user";
import {Router} from "@angular/router";
import {DataTransferService} from "../../services/data-transfer.service";

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
  dataService: DataTransferService;

  constructor(private createUserSrv: CreateUserService, private dataSrv: DataTransferService, private routerParam: Router) {
    this.createUserService = createUserSrv;
    this.dataService = dataSrv;
    this.router = routerParam;
  }

  ngOnInit() {
  }

  createUser() {
    this.createUserButtonClicked = true;
    let newUser = new User(this.userName, this.userEmail, this.password);
    this.createUserService.createUser(newUser).subscribe(
      createdUser => {
        this.createdUser = createdUser;
      },
      error => {
        console.log(error);
      }, () => {
        this.dataSrv.setLoggedInUser(this.createdUser);
        this.router.navigate(['createUserSuccess'])
      });
  }

  inputIsInvalid(): boolean {
    if (this.createUserButtonClicked) {
      let errorMsg: string;
      errorMsg = InputValidator.validateUserCreationInput(this.userEmail, this.userName, this.password, this.passwordReEntered);
      if (errorMsg) {
        this.errorMessage = errorMsg;
        return true;
      }
    }
    return false;
  }
}

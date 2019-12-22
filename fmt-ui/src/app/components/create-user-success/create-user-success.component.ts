import {Component, OnInit} from '@angular/core';
import {DataTransferService} from "../../services/data-transfer.service";
import {User} from "../../domain/user";

@Component({
  selector: 'app-create-user-success',
  templateUrl: './create-user-success.component.html',
  styleUrls: ['./create-user-success.component.css']
})
export class CreateUserSuccessComponent implements OnInit {
  loggedUserName: string;
  loggedUser: User;
  dataSrv: DataTransferService;

  constructor(private dataService: DataTransferService) {
    this.dataSrv = this.dataService;
  }

  ngOnInit() {
    this.dataSrv.loggedInUser.subscribe(user => {
      this.loggedUser = user;
      this.loggedUserName = this.loggedUser.name;
    })
  }

}

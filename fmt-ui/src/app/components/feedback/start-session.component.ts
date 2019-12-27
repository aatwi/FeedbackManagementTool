import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-start-session',
  templateUrl: './start-session.component.html'
})
export class StartSessionComponent implements OnInit {

  sessionName: string;
  sessionStartDate: Date;
  sessionEndDate: Date;

  constructor() {
  }

  ngOnInit() {
  }

  createSession() {
    console.log(this.sessionStartDate);
    console.log(this.sessionEndDate);
  }
}

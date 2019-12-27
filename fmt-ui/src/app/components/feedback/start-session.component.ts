import {Component, OnInit} from '@angular/core';
import {Session} from "../../domain/session";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatDatepickerInputEvent} from "@angular/material";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {Moment} from "moment";

export const DATE_FORMAT = {
  parse: {
    dateInput: 'YYYY-MM-DD',
  },
  display: {
    dateInput: 'YYYY-MM-DD'
  },
};

@Component({
  selector: 'app-start-session',
  templateUrl: './start-session.component.html',
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: DATE_FORMAT}
  ]
})
export class StartSessionComponent implements OnInit {

  sessionName: string;
  sessionStartDate: Date;
  sessionEndDate: Date;

  createdSession: Session;
  minStartDate = new Date();
  minEndDate = new Date(new Date().setDate(this.minStartDate.getDate() + 1));

  constructor() {
  }

  ngOnInit() {
  }

  createSession() {
    this.createdSession = new Session(this.sessionName, this.sessionStartDate, this.sessionEndDate);
  }

  changeStartDate(event: MatDatepickerInputEvent<Moment>) {
    this.sessionStartDate = event.value.toDate();
    this.minEndDate = new Date(new Date().setDate(this.sessionStartDate.getDate() + 1));
  }

  changeEndDate(event: MatDatepickerInputEvent<Moment>) {
    this.sessionEndDate = event.value.toDate();
  }
}

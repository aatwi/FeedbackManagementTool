import {Component, OnInit} from '@angular/core';
import {Session} from "../../domain/session";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatDatepickerInputEvent} from "@angular/material";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {Moment} from "moment";
import {DateHelper} from "../../helpers/date-helper";

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
  minEndDate = DateHelper.getNextDayOf(this.minStartDate);

  constructor() {
  }

  ngOnInit() {
  }

  createSession() {
    this.createdSession = new Session(this.sessionName, this.sessionStartDate, this.sessionEndDate);
  }

  changeStartDate(event: MatDatepickerInputEvent<Moment>) {
    this.sessionStartDate = event.value.toDate();
    this.minEndDate = DateHelper.getNextDayOf(this.minStartDate);
  }

  changeEndDate(event: MatDatepickerInputEvent<Moment>) {
    this.sessionEndDate = event.value.toDate();
  }
}

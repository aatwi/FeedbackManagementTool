import {async, TestBed} from '@angular/core/testing';

import {StartSessionComponent} from './start-session.component';
import {Session} from "../../domain/session";
import {AppModule} from "../../app.module";

describe('StartSessionComponent', () => {
  let sessionComponent: StartSessionComponent;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        AppModule],
      declarations: []
    })
      .compileComponents().then(() => {
      sessionComponent = TestBed.createComponent(StartSessionComponent).componentInstance;
      sessionComponent.sessionName = 'Session Name';
      sessionComponent.sessionStartDate = new Date(2019, 12, 31);
      sessionComponent.sessionEndDate = new Date(2020, 1, 15);
    });
  }));

  it('Should create an instance of the Session object when the method createSession is called', () => {
    sessionComponent.createSession();
    const expectedSession: Session = new Session('Session Name', new Date(2019, 12, 31), new Date(2020, 1, 15));
    expect(sessionComponent.createdSession).toEqual(expectedSession);
  });

});

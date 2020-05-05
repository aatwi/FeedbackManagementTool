export class Session {
  name: string;
  startDate: Date;
  endDate: Date;

  constructor(name: string, startDate: Date, endDate: Date) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}

export class DateHelper {
  static getNextDayOf(input: Date): Date {
    let nextDay: Date = new Date();
    nextDay.setDate(input.getDate() + 1);
    nextDay.setHours(0, 0, 0, 0);
    return nextDay;
  }
}

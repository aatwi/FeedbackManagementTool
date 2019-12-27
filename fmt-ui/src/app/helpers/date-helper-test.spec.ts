import {DateHelper} from "./date-helper";

describe("DateHelper Tests:", () => {

  it("Should add one day to the given date", () => {
    let input: Date = new Date(2019, 12, 31)
    let expected: Date = new Date(2020, 0, 1);


    let actual: Date = DateHelper.getNextDayOf(input);
    expect(actual).toEqual(expected);
  })
});

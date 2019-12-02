import {InputValidator} from "./input-validator";

describe('InputValidator', () => {
  it('isValidString should return false when password is empty', () => {
    expect(InputValidator.isValidString("")).toEqual(false);
  });

  it('isValidString should return false when password undefined', () => {
    expect(InputValidator.isValidString(undefined)).toEqual(false);
  });

  it('isValidString should return true when password is properly defined', () => {
    expect(InputValidator.isValidString("password")).toEqual(true);
  });

  it('isEmailValid should return false when email empty', () => {
    expect(InputValidator.isEmailValid('')).toEqual(false);
  });

  it('isEmailValid should return false when email is undefined', () => {
    expect(InputValidator.isEmailValid(undefined)).toEqual(false);
  });

  it('isEmailValid should return false when email without the @', () => {
    expect(InputValidator.isEmailValid('user.com')).toEqual(false);
  });

  it('isEmailValid should return false when email without the @ and .domain ', () => {
    expect(InputValidator.isEmailValid('user')).toEqual(false);
  });

  it('isEmailValid should return false when email without the domain ', () => {
    expect(InputValidator.isEmailValid('user@fmt.')).toEqual(false);
  });

  it('isEmailValid should return false when email without the .domain ', () => {
    expect(InputValidator.isEmailValid('user@fmt')).toEqual(false);
  });

  it('isEmailValid should return true when email is valid', () => {
    expect(InputValidator.isEmailValid('user@fmt.com')).toEqual(true);
  });
});

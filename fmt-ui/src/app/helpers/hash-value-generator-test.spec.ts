import {HashValueGenerator} from './hash-value-generator';

describe('HashValueGenerator', () => {
  it('should generate the 256 hash value of a password', () => {
    const expected = '0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e';

    const actual = HashValueGenerator.getHashFor('password1');
    expect(actual).toEqual(expected);
  });
});

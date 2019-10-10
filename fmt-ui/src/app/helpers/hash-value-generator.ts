import {sha256} from 'js-sha256';

export class HashValueGenerator {
  static getHashFor(password: string): any {
    const hash = sha256.create();
    hash.update(password);
    return hash.toString();
  }
}

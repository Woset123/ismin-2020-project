import { Injectable } from '@nestjs/common';
import { Town } from './Town';

@Injectable()
export class AppService {
  private readonly storage: Map<string, Town> = new Map();

  addTown(town: Town): void {
    this.storage.set(town.City, town);
  }

  getTown(name: string): Town | undefined {
    return this.storage.get(name);
  }

  deleteTown(name: string): boolean {
    return this.storage.delete(name);
  }

  getTownsOf(country: string): Town[] {
    return Array.from(this.storage.values()).filter(
      town => town.Country === country,
    );
  }

  getAllTowns() {
    return Array.from(this.storage.values()).sort((townA, townB) =>
      townA.City.localeCompare(townB.City),
    );
  }

  getTotalNumberOfTowns() {
    return this.storage.size;
  }
}

import { Injectable } from '@nestjs/common';
import { Town } from './Town';
import * as DataTown1 from '../csvjson.json';
import * as DataTown2 from '../csvjsonfav.json';

const donnee1: Map<string, Town> = new Map();
const donnee2: Map<string, Town> = new Map();

const numberOfTowns1: number = Object.keys(DataTown1).length - 1;
for(let i=0; i < numberOfTowns1; i++){
  donnee1.set(DataTown1[i].City, DataTown1[i])
}
const numberOfTowns2: number = Object.keys(DataTown2).length - 1;
for(let i=0; i < numberOfTowns2; i++){
  donnee2.set(DataTown2[i].City, DataTown2[i])
}

@Injectable()
export class AppService {


  private readonly storage1: Map<string, Town> = new Map(donnee1);
  private readonly storage2: Map<string, Town> = new Map(donnee2);

  addTown(town: Town): void {
    this.storage1.set(town.City, town);
  }
  addfavTown(town: Town): void {
    this.storage2.set(town.City, town);
  }

  getTown(name: string): Town | undefined {
    return this.storage1.get(name);
  }

  getfavTown(name: string): Town | undefined {
    return this.storage2.get(name);
  }

  deleteTown(name: string): boolean {
    return this.storage1.delete(name);
  }

  deletefavTown(name: string): boolean {
    return this.storage2.delete(name);
  }

  getTownsOf(country: string): Town[] {
    return Array.from(this.storage1.values()).filter(
        town => town.Country === country,
    );
  }
  getfavTownsOf(country: string): Town[] {
    return Array.from(this.storage2.values()).filter(
        town => town.Country === country,
    );
  }

  getAllTowns() {
    return Array.from(this.storage1.values()).sort((townA, townB) =>
        townA.City.localeCompare(townB.City),
    );
  }
  getAllfavTowns() {
    return Array.from(this.storage2.values()).sort((townA, townB) =>
        townA.City.localeCompare(townB.City),
    );
  }
  getTotalNumberOfTowns() {
    return this.storage1.size;
  }
  getTotalNumberOffavTowns() {
    return this.storage2.size;
  }
}
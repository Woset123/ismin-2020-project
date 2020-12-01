import { Town } from './Town';

export class TownList {
  public townList:Town[];
  s: Town[]=[];
  nothere = true;

  constructor() {
    this.townList = [];
  };

  addTown(town: Town) {
    for(let i=0; i < this.townList.length; i++)
    {
      if (this.townList[i] === town) {
        this.nothere = false ;
      }
    }
    if (this.nothere) {
      this.townList.push(town);
    }
    this.townList.sort((a,b) => {return a.City.localeCompare(b.City);});
  };

  getTown(name: string) {
    for(let i=0; i < this.townList.length; i++)
    {
      if (this.townList[i].City === name) {
        return this.townList[i];
      }
    }
  };

  getTownsOf(country: string) {
    this.s = [];
    for(let i=0; i < this.townList.length; i++)
    {
      if (this.townList[i].Country === country) {
        this.s.push(this.townList[i]);
      }
    }
    return this.s;
  };

  getAllTowns() {
    return this.townList;
  };

  getTotalNumberOfTowns() {
    return this.townList.length;
  };

}
import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Query,
} from '@nestjs/common';
import { AppService } from './app.service';
import { Town } from './Town';

@Controller('towns')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getTowns(@Query('Country') Country: string): Town[] {
    if (Country) {
      return this.appService.getTownsOf(Country);
    }
    return this.appService.getAllTowns();
  }
  @Get('/favoris')
  getfavTowns(@Query('Country') Country: string): Town[] {
    if (Country) {
      return this.appService.getfavTownsOf(Country);
    }
    return this.appService.getAllfavTowns();
  }

  @Post()
  createTown(@Body() newTown: Town): Town {
    this.appService.addTown(newTown);

    return this.appService.getTown(newTown.City);
  }

  @Post('/favoris')
  createfavTown(@Body() newTown: Town): Town {
    this.appService.addfavTown(newTown);

    return this.appService.getfavTown(newTown.City);
  }

  @Get('/:City')
  getTown(@Param('City') City): Town {
    return this.appService.getTown(City);
  }

  @Get('/favoris/:City')
  getfavTown(@Param('City') City): Town {
    return this.appService.getfavTown(City);
  }

  @Get('/:City')
  getTotalNumberOfTowns(): number {
    return this.appService.getTotalNumberOfTowns();
  }

  @Get('/favoris/:City')
  getTotalNumberOffavTowns(): number {
    return this.appService.getTotalNumberOffavTowns();
  }

  @Delete('/:City')
  deleteTown(@Param('City') City): void {
    this.appService.deleteTown(City);
    this.appService.deletefavTown(City);
  }
  @Delete('/favoris/:City')
  deletefavTown(@Param('City') City): void {
    this.appService.deletefavTown(City);
  }
}


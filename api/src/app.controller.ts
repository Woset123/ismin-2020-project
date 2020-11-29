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
  getTowns(@Query('country') country: string): Town[] {
    if (country) {
      return this.appService.getTownsOf(country);
    }
    return this.appService.getAllTowns();
  }

  @Post()
  createTown(@Body() newTown: Town): Town {
    this.appService.addTown(newTown);

    return this.appService.getTown(newTown.City);
  }

  @Get('/:City')
  getTown(@Param('City') City): Town {
    return this.appService.getTown(City);
  }

  @Delete('/:City')
  deleteTown(@Param('City') City): void {
    this.appService.deleteTown(City);
  }
}


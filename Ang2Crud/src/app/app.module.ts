import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {PersonService} from './person.service';

import { AppComponent } from './app.component';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
//    InMemoryWebApiModule.forRoot(InMemoryDataService),
  ],
  providers: [PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }

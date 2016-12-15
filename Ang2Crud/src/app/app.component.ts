import { Component, OnInit } from '@angular/core';

import { Person } from './person';
import { PersonService } from './person.service';

@Component({
  selector: 'person-crud',
  templateUrl: './person-crud.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'Person CRUD';
  persons: Person[] = [];

  constructor(private personService: PersonService) { }

  ngOnInit(): void {
    this.personService.getPersons().then(persons => this.persons=persons);
  }
}

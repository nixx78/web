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
  selectedPerson: Person;

  constructor(private personService: PersonService) { }

  ngOnInit(): void {
    this.personService.getPersons().then(persons => this.persons=persons);
   	this.selectedPerson = this.persons[1];
  }


  onSelect(person:Person) :void {
  	this.selectedPerson = person;
  }

  save() :void {
  	this.personService.save(this.selectedPerson);
  }

  delete() :void {
  	this.personService.delete(this.selectedPerson);
  	
  }


}

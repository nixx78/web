import 'rxjs/add/operator/switchMap';
import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Person }        from './person';
import { PersonService } from './person.service';

@Component({
  moduleId: module.id,
  selector: 'person-details',
  templateUrl: 'person-details.html',
})

export class PersonDetailsComponent implements OnInit {

  person: Person;

  constructor(
    private personService: PersonService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.personService.getPerson(+params['id']))
      .subscribe(person => this.person = person);
  }

/*
  save(): void {
    this.personService.update(this.person).then(() => this.goBack());
  }
*/

  goBack(): void {
    this.location.back();
  }
}

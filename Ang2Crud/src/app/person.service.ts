import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import { environment } from '../environments/environment';


import 'rxjs/add/operator/toPromise';

import { Person } from './person';

@Injectable()
export class PersonService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private personUrl = environment.rest_api;
  
  constructor(private http: Http) { }

  getPersons(): Promise<Person[]> {
    return this.http
    	   .get(this.personUrl)
           .toPromise()
           .then(response => response.json() as Person[])
           .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
  
}

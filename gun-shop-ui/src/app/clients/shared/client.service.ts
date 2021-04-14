import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Client} from './client.model';
import {Observable} from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
@Injectable()
export class ClientService {

  private clientsUrl = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(this.clientsUrl);
  }
}

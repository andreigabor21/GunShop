import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Client} from './client.model';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

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

  // saveClient(client: Client): Observable<Client>{
  //   console.log(client);
  //   return this.httpClient.post<Client>(this.clientsUrl, client);
  // }

  saveClient(client: Client): any{
      console.log(client);
      console.log(JSON.stringify(client));
      return this.httpClient.post<Client>(this.clientsUrl, client);
    }


  getClient(id: number): Observable<Client> {
    return this.getClients()
      .pipe(
        map(clients => clients.find(client => client.id === id))
      );
  }

  updateClient(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(url, client);
  }

  deleteClient(client: Client): any {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .delete(url);
  }
}

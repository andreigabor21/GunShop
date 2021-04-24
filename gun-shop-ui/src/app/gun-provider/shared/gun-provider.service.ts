import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../../clients/shared/client.model';
import {map} from 'rxjs/operators';
import {GunProvider} from './gun-provider.model';

@Injectable({
  providedIn: 'root'
})
export class GunProviderService {
  private gunProviderUrl = 'http://localhost:8080/api/gun-providers';

  constructor(private httpClient: HttpClient) { }

  getGunProviders(): Observable<GunProvider[]> {
    return this.httpClient.get<GunProvider[]>(this.gunProviderUrl);
  }

  // saveClient(client: Client): Observable<Client>{
  //   console.log(client);
  //   return this.httpClient.post<Client>(this.clientsUrl, client);
  // }
  //
  // getClient(id: number): Observable<Client> {
  //   return this.getClients()
  //     .pipe(
  //       map(clients => clients.find(client => client.id === id))
  //     );
  // }
  //
  // updateClient(client: Client): Observable<Client> {
  //   const url = `${this.clientsUrl}/${client.id}`;
  //   return this.httpClient
  //     .put<Client>(url, client);
  // }
  //
  // deleteClient(client: Client): any {
  //   const url = `${this.clientsUrl}/${client.id}`;
  //   return this.httpClient
  //     .delete(url);
  // }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Client} from './client.model';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Address} from './address.model';
import {GunType} from '../../gun-type/shared/gun-type.model';
import {Rental} from '../../rental/shared/rental.model';

// @Injectable({
//   providedIn: 'root'
// })
@Injectable()
export class ClientService {

  private clientsUrl = 'http://localhost:8080/api/clients';
  private rentalsUrl = 'http://localhost:8080/api/rentals';

  constructor(private httpClient: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(this.clientsUrl);
  }

  // saveClient(client: Client): Observable<Client>{
  //   console.log(client);
  //   return this.httpClient.post<Client>(this.clientsUrl, client);
  // }

  saveClient(client: Client, address: Address): Observable<Client>{
      console.log(client);
      console.log(JSON.stringify(client));
      console.log(address);
      client.address = address;
      console.log(client);
      return this.httpClient.post<Client>(this.clientsUrl, client);
    }


  getClient(id: number): Observable<Client> {
    return this.getClients()
      .pipe(
        map(clients => clients.find(client => client.id === id))
      );
  }

  updateClient(client: Client): Observable<Client> {
    const dob = client.dateOfBirth;
    console.log(client);
    const date = new Date(dob);
    date.setHours(19);
    const dateJSON = date.toJSON();
    console.log(dateJSON);
    client.dateOfBirth = dateJSON;
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(url, client);
  }

  deleteClient(client: Client): any {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .delete(url);
  }

  createRental(client: Client, selectedGunType: GunType): any {
    const clientId = client.id;
    const gunTypeId = selectedGunType.id;
    const price = 100;
    const rental: Rental = {clientId, gunTypeId, price};
    console.log('rental: ', rental);
    return this.httpClient.post(this.rentalsUrl, rental);
  }
}

import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {GunProvider} from '../../gun-provider/shared/gun-provider.model';
import {HttpClient} from '@angular/common/http';
import {Rental} from './rental.model';
import {GunType} from '../../gun-type/shared/gun-type.model';

@Injectable({
  providedIn: 'root'
})
export class RentalService {

  private rentalsUrl = 'http://localhost:8080/api/rentals';

  constructor(private httpClient: HttpClient) { }

  getRentals(): Observable<Rental[]> {
    return this.httpClient.get<Rental[]>(this.rentalsUrl);
  }

  getMostRentedGun(): Observable<GunType> {
    return this.httpClient.get<GunType>(this.rentalsUrl + '/most-rented');
  }
}

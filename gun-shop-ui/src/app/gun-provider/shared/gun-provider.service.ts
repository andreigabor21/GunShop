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

  saveGunProvider(gunProvider: GunProvider): Observable<GunProvider>{
    console.log(gunProvider);
    return this.httpClient.post<GunProvider>(this.gunProviderUrl, gunProvider);
  }

  getGunProvider(id: number): Observable<GunProvider> {
    return this.getGunProviders()
      .pipe(
        map(gunProviders => gunProviders.find(gp => gp.id === id))
      );
  }

  updateGunProvider(gunProvider: GunProvider): Observable<GunProvider> {
    const url = `${this.gunProviderUrl}/${gunProvider.id}`;
    return this.httpClient
      .put<GunProvider>(url, gunProvider);
  }

  deleteGunProvider(gunProvider: GunProvider): any {
    const url = `${this.gunProviderUrl}/${gunProvider.id}`;
    return this.httpClient
      .delete(url);
  }

  filterGunProvidersByReputation(reputation: number): Observable<GunProvider[]> {
    const url = `${this.gunProviderUrl}/filter?reputation=${reputation}`;
    return this.httpClient.get<GunProvider[]>(url);
  }

  getGunProvidersSorted(): Observable<GunProvider[]> {
    const url = `${this.gunProviderUrl}/sort/name`;
    return this.httpClient.get<GunProvider[]>(url);
  }
}

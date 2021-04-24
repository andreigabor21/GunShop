import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GunProvider} from '../../gun-provider/shared/gun-provider.model';
import {GunType} from './gun-type.model';
import {Client} from '../../clients/shared/client.model';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GunTypeService {

  private gunTypeUrl = 'http://localhost:8080/api/gun-types';

  constructor(private httpClient: HttpClient) { }

  getGunTypes(): Observable<GunType[]> {
    return this.httpClient.get<GunType[]>(this.gunTypeUrl);
  }

  saveGunType(gunType: GunType): Observable<GunType>{
    console.log(gunType);
    return this.httpClient.post<GunType>(this.gunTypeUrl, gunType);
  }


  getGunType(id: number): Observable<GunType> {
    return this.getGunTypes()
      .pipe(
        map(gunTypes => gunTypes.find(gunType => gunType.id === id))
      );
  }

  updateGunType(gunType: GunType): Observable<GunType> {
    const url = `${this.gunTypeUrl}/${gunType.id}`;
    return this.httpClient
      .put<GunType>(url, gunType);
  }

  deleteGunType(gunType: GunType): any {
    const url = `${this.gunTypeUrl}/${gunType.id}`;
    return this.httpClient
      .delete(url);
  }
}

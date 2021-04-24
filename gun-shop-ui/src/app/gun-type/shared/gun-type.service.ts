import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GunProvider} from '../../gun-provider/shared/gun-provider.model';
import {GunType} from './gun-type.model';

@Injectable({
  providedIn: 'root'
})
export class GunTypeService {

  private gunTypeUrl = 'http://localhost:8080/api/gun-types';

  constructor(private httpClient: HttpClient) { }

  getGunTypes(): Observable<GunType[]> {
    return this.httpClient.get<GunType[]>(this.gunTypeUrl);
  }
}

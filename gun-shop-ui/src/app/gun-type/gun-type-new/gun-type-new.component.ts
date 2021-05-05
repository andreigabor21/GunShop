import { Component, OnInit } from '@angular/core';
import {GunProviderService} from '../../gun-provider/shared/gun-provider.service';
import {Router} from '@angular/router';
import {GunProvider} from '../../gun-provider/shared/gun-provider.model';
import {GunTypeService} from '../shared/gun-type.service';
import {GunType} from '../shared/gun-type.model';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-gun-type-new',
  templateUrl: './gun-type-new.component.html',
  styleUrls: ['./gun-type-new.component.css']
})
export class GunTypeNewComponent implements OnInit {

  gunProviders: GunProvider[];
  selectedProvider: GunProvider;

  constructor(private gunTypeService: GunTypeService,
              private gunProviderService: GunProviderService,
              private router: Router) { }

  ngOnInit(): void {
    this.gunProviderService.getGunProviders()
      .subscribe(
        data => {
          this.gunProviders = data;
          console.log(data);
        },
        error => console.log(error)
      );
  }

  addGunType(name: string, category: string): void {
    const gunType = { name, category } as GunType;
    gunType.gunProvider = this.selectedProvider;
    console.log(gunType);
    this.gunTypeService.saveGunType(gunType)
      .subscribe(() => this.router.navigateByUrl('/gun-types'));
  }
}

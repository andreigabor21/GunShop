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

  constructor(private gunTypeService: GunTypeService,
              private router: Router) { }

  ngOnInit(): void {
  }

  addGunType(name: string, category: string, gunProviderId: number): void {
    const gunType = { name, category, gunProviderId} as GunType;
    this.gunTypeService.saveGunType(gunType)
      .subscribe(() => this.router.navigateByUrl('/gun-types'));
  }
}

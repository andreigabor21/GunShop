import { Component, OnInit } from '@angular/core';
import {Client} from '../../clients/shared/client.model';
import {GunProviderService} from '../shared/gun-provider.service';
import {GunProvider} from '../shared/gun-provider.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-gun-provider-new',
  templateUrl: './gun-provider-new.component.html',
  styleUrls: ['./gun-provider-new.component.css']
})
export class GunProviderNewComponent implements OnInit {

  constructor(private gunProviderService: GunProviderService,
              private router: Router) { }

  ngOnInit(): void {
  }

  addGunProvider(name: string, speciality: string, reputation: number): void {
    const gunProvider = { name, speciality, reputation} as GunProvider;
    this.gunProviderService.saveGunProvider(gunProvider)
      .subscribe(() => this.router.navigateByUrl('/gun-providers'));
  }

}

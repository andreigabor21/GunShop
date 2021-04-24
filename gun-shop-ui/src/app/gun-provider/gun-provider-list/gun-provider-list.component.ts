import { Component, OnInit } from '@angular/core';
import {Client} from '../../clients/shared/client.model';
import {ClientService} from '../../clients/shared/client.service';
import {Router} from '@angular/router';
import {GunProvider} from '../shared/gun-provider.model';
import {GunProviderService} from '../shared/gun-provider.service';

@Component({
  selector: 'app-gun-provider-list',
  templateUrl: './gun-provider-list.component.html',
  styleUrls: ['./gun-provider-list.component.css']
})
export class GunProviderListComponent implements OnInit {

  errorMessage: string;
  gunProviders: GunProvider[];
  selectedGunProvider: GunProvider;

  constructor(private gunProviderService: GunProviderService,
              private router: Router) { }

  ngOnInit(): void {
    this.getGunProviders();
  }

  getGunProviders(): void {
    this.gunProviderService.getGunProviders()
      .subscribe(
        data => {
          this.gunProviders = data;
          console.log(data);
        },
        error => this.errorMessage = error
      );
  }

  onSelect(gunProvider: GunProvider): void {
    this.selectedGunProvider = gunProvider;
  }

  goToDetails(): void {
    this.router.navigate(['/gun-provider/detail', this.selectedGunProvider.id]);
  }
}

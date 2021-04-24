import {Component, Input, OnInit} from '@angular/core';
import {Client} from '../../clients/shared/client.model';
import {ClientService} from '../../clients/shared/client.service';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {switchMap} from 'rxjs/operators';
import {GunProvider} from '../shared/gun-provider.model';
import {GunProviderService} from '../shared/gun-provider.service';

@Component({
  selector: 'app-gun-provider-detail',
  templateUrl: './gun-provider-detail.component.html',
  styleUrls: ['./gun-provider-detail.component.css']
})
export class GunProviderDetailComponent implements OnInit {

  @Input() gunProvider: GunProvider;

  constructor(private gunProviderService: GunProviderService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.gunProviderService.getGunProvider(+params['id'])))
      .subscribe(gp => this.gunProvider = gp);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.gunProviderService.updateGunProvider(this.gunProvider)
      .subscribe(() => this.goBack());
  }

  delete(): void {
    this.gunProviderService.deleteGunProvider(this.gunProvider)
      .subscribe(() => this.goBack());
  }
}

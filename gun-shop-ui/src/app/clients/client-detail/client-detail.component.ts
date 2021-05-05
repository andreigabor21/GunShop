import {Component, Input, OnInit} from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {ActivatedRoute, Params} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {Location} from '@angular/common';
import {GunTypeService} from '../../gun-type/shared/gun-type.service';
import {GunType} from '../../gun-type/shared/gun-type.model';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css']
})
export class ClientDetailComponent implements OnInit {

  @Input() client: Client;

  gunTypes: GunType[];
  selectedGunType: GunType;

  constructor(private clientService: ClientService,
              private gunTypeService: GunTypeService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.clientService.getClient(+params['id'])))
      .subscribe(client => this.client = client);
    this.gunTypeService.getGunTypes()
      .subscribe(
        data => {
          this.gunTypes = data;
          console.log(data);
        },
        error => console.log(error)
      );
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.clientService.updateClient(this.client)
      .subscribe(() => this.goBack());
  }

  delete(): void {
    this.clientService.deleteClient(this.client)
      .subscribe(() => this.goBack());
  }

  rent(): void {
    console.log(this.client);
    console.log(this.selectedGunType);
    this.clientService.createRental(this.client, this.selectedGunType)
      .subscribe(() => this.goBack());
  }
}

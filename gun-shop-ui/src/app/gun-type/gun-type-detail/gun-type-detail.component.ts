import {Component, Input, OnInit} from '@angular/core';
import {GunProvider} from '../../gun-provider/shared/gun-provider.model';
import {GunProviderService} from '../../gun-provider/shared/gun-provider.service';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {switchMap} from 'rxjs/operators';
import {GunType} from '../shared/gun-type.model';
import {GunTypeService} from '../shared/gun-type.service';

@Component({
  selector: 'app-gun-type-detail',
  templateUrl: './gun-type-detail.component.html',
  styleUrls: ['./gun-type-detail.component.css']
})
export class GunTypeDetailComponent implements OnInit {

  @Input() gunType: GunType;

  constructor(private gunTypeService: GunTypeService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.gunTypeService.getGunType(+params['id'])))
      .subscribe(gt => this.gunType = gt);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.gunTypeService.updateGunType(this.gunType)
      .subscribe(() => this.goBack());
  }

  delete(): void {
    this.gunTypeService.deleteGunType(this.gunType)
      .subscribe(() => this.goBack());
  }

}

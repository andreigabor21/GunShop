import {Component, Input, OnInit} from '@angular/core';
import {GunProvider} from '../../gun-provider/shared/gun-provider.model';
import {GunProviderService} from '../../gun-provider/shared/gun-provider.service';
import {Router} from '@angular/router';
import {GunType} from '../shared/gun-type.model';
import {GunTypeService} from '../shared/gun-type.service';

@Component({
  selector: 'app-gun-type-list',
  templateUrl: './gun-type-list.component.html',
  styleUrls: ['./gun-type-list.component.css']
})
export class GunTypeListComponent implements OnInit {

  errorMessage: string;
  gunTypes: GunType[];
  selectedGunType: GunType;
  @Input() typeFilter: string;

  constructor(private gunTypeService: GunTypeService,
              private router: Router) { }

  ngOnInit(): void {
    this.getGunTypes();
  }

  getGunTypes(): void {
    this.gunTypeService.getGunTypes()
      .subscribe(
        data => {
          this.gunTypes = data;
          console.log(data);
        },
        error => this.errorMessage = error
      );
  }

  onSelect(gunType: GunType): void {
    this.selectedGunType = gunType;
  }

  goToDetails(): void {
    this.router.navigate(['/gun-type/detail', this.selectedGunType.id]);
  }

  filterByType(): void {
    console.log(this.typeFilter);
    if (this.typeFilter === '') {
      this.ngOnInit();
    } else {
      this.gunTypes = this.gunTypes.filter(res => {
        return res.category.toLowerCase().match(this.typeFilter.toLocaleLowerCase());
      });
    }
  }
}

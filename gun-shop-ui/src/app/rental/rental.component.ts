import { Component, OnInit } from '@angular/core';
import {Rental} from './shared/rental.model';
import {RentalService} from './shared/rental.service';
import {GunType} from '../gun-type/shared/gun-type.model';
import {FullRental} from './shared/rental-full.model';

@Component({
  selector: 'app-rental',
  templateUrl: './rental.component.html',
  styleUrls: ['./rental.component.css']
})
export class RentalComponent implements OnInit {

  rentals: FullRental[];
  mostRentedGun: GunType;
  errorMessage: string;

  displayedColumns: string[] = ['client-name', 'gun-weight', 'price'];
  // dataSource = this.rentals;

  constructor(private rentalService: RentalService) { }

  ngOnInit(): void {
    this.getGunProviders();
    this.getMostRentedGunType();
  }

  getGunProviders(): void {
    this.rentalService.getRentals()
      .subscribe(
        data => {
          this.rentals = data;
          console.log(data);
        },
        error => this.errorMessage = error
      );
  }

  getMostRentedGunType(): void {
    this.rentalService.getMostRentedGun()
      .subscribe(
        data => {
          this.mostRentedGun = data;
          console.log(data);
        },
        error => this.errorMessage = error
      );
  }

}

import { Component, OnInit } from '@angular/core';
import {Rental} from './shared/rental.model';
import {RentalService} from './shared/rental.service';

@Component({
  selector: 'app-rental',
  templateUrl: './rental.component.html',
  styleUrls: ['./rental.component.css']
})
export class RentalComponent implements OnInit {

  rentals: Rental[];
  errorMessage: string;

  constructor(private rentalService: RentalService) { }

  ngOnInit(): void {
    this.getGunProviders();
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

}

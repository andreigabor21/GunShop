import { Component, OnInit } from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {Router} from '@angular/router';
import {FormBuilder, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private clientService: ClientService,
              private router: Router) { }

  clientForm = this.formBuilder.group({
    name: ['', Validators.required],
    dateOfBirth: '',
  });

  addressForm = this.formBuilder.group({
    city: '',
    street: '',
    number: ''
  });

  ngOnInit(): void {
  }

  onFormSubmit(): void {
    console.log(this.clientForm.value);
    this.clientService.saveClient(this.clientForm.value, this.addressForm.value)
      .subscribe(() => this.router.navigateByUrl('/clients'));
    this.clientForm.reset();
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.clientForm.controls[controlName].hasError(errorName);
  }

}

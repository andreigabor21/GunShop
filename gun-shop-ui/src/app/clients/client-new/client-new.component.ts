import { Component, OnInit } from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {Router} from '@angular/router';
import {FormBuilder, FormControl} from '@angular/forms';

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
    name: '',
    dateOfBirth: '',
  });

  ngOnInit(): void {
  }

  onFormSubmit(): void {
    this.clientService.saveClient(this.clientForm.value)
      .subscribe(() => this.router.navigateByUrl('/clients'));
    this.clientForm.reset();
  }

}

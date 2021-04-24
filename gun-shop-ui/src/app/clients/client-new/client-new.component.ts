import { Component, OnInit } from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';

@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
  }

  addClient(name: string, dob: string): void {
    const dateOfBirth = dob.split('-').map(x => +x);
    const client: Client = {name, dateOfBirth} as Client;
    this.clientService.saveClient(client)
      .subscribe(client => console.log('saved: ', client));
  }
}

import { Component, OnInit } from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  errorMessage: string;
  clients: Client[];
  selectedClient: Client;

  constructor(private clientService: ClientService,
              private router: Router) { }

  ngOnInit(): void {
    this.getClients();
  }

  getClients(): void {
    this.clientService.getClients()
      .subscribe(
        clients => {
          this.clients = clients;
          console.log(clients);
        },
          error => this.errorMessage = error
      );
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  goToDetails(): void {
    this.router.navigate(['/client/detail', this.selectedClient.id]);
  }
}

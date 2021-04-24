import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import { AppComponent } from './app.component';
import { ClientsComponent } from './clients/clients.component';
import {AppRoutingModule} from './app-routing.module';
import { ClientListComponent } from './clients/client-list/client-list.component';
import {ClientService} from './clients/shared/client.service';
import {HttpClientModule} from '@angular/common/http';
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { ClientDetailComponent } from './clients/client-detail/client-detail.component';
import {FormsModule} from '@angular/forms';
import { GunProviderComponent } from './gun-provider/gun-provider.component';
import { GunTypeComponent } from './gun-type/gun-type.component';
import { GunProviderListComponent } from './gun-provider/gun-provider-list/gun-provider-list.component';
import { GunTypeListComponent } from './gun-type/gun-type-list/gun-type-list.component';


@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    ClientListComponent,
    ClientNewComponent,
    ClientDetailComponent,
    GunProviderComponent,
    GunTypeComponent,
    GunProviderListComponent,
    GunTypeListComponent
  ],
    imports: [
        BrowserModule,
        RouterModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
    ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }

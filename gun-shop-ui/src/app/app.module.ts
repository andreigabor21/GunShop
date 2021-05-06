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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { GunProviderComponent } from './gun-provider/gun-provider.component';
import { GunTypeComponent } from './gun-type/gun-type.component';
import { GunProviderListComponent } from './gun-provider/gun-provider-list/gun-provider-list.component';
import { GunTypeListComponent } from './gun-type/gun-type-list/gun-type-list.component';
import { GunProviderDetailComponent } from './gun-provider/gun-provider-detail/gun-provider-detail.component';
import { GunProviderNewComponent } from './gun-provider/gun-provider-new/gun-provider-new.component';
import { GunTypeNewComponent } from './gun-type/gun-type-new/gun-type-new.component';
import { GunTypeDetailComponent } from './gun-type/gun-type-detail/gun-type-detail.component';

import { MatDatepickerModule } from '@angular/material/datepicker';
import {MatLineModule, MatNativeDateModule} from '@angular/material/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import { MatTableModule } from '@angular/material/table'
import {MatSelectModule} from '@angular/material/select';
import { RentalComponent } from './rental/rental.component';


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
    GunTypeListComponent,
    GunProviderDetailComponent,
    GunProviderNewComponent,
    GunTypeNewComponent,
    GunTypeDetailComponent,
    RentalComponent
  ],
  imports: [
    MatTableModule,
    BrowserModule,
    RouterModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule, MatLineModule, MatListModule, MatSelectModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }

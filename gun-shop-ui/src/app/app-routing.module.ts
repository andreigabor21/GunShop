import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from './clients/clients.component';
import {ClientNewComponent} from './clients/client-new/client-new.component';
import {ClientDetailComponent} from './clients/client-detail/client-detail.component';
import {GunProviderComponent} from './gun-provider/gun-provider.component';
import {GunTypeComponent} from './gun-type/gun-type.component';
import {GunProviderDetailComponent} from './gun-provider/gun-provider-detail/gun-provider-detail.component';
import {GunProviderNewComponent} from './gun-provider/gun-provider-new/gun-provider-new.component';
import {GunTypeNewComponent} from './gun-type/gun-type-new/gun-type-new.component';
import {GunTypeDetailComponent} from './gun-type/gun-type-detail/gun-type-detail.component';
import {RentalComponent} from './rental/rental.component';

const routes: Routes = [
  {path: 'clients', component: ClientsComponent},
  {path: 'clients-new', component: ClientNewComponent},
  {path: 'client/detail/:id', component: ClientDetailComponent},

  {path: 'gun-providers', component: GunProviderComponent},
  {path: 'gun-provider/detail/:id', component: GunProviderDetailComponent},
  {path: 'gun-providers-new', component: GunProviderNewComponent},

  {path: 'gun-types', component: GunTypeComponent},
  {path: 'gun-type/detail/:id', component: GunTypeDetailComponent},
  {path: 'gun-types-new', component: GunTypeNewComponent},

  {path: 'rentals', component: RentalComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

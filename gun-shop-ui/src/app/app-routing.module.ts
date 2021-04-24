import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from './clients/clients.component';
import {ClientNewComponent} from './clients/client-new/client-new.component';
import {ClientDetailComponent} from './clients/client-detail/client-detail.component';
import {GunProviderComponent} from './gun-provider/gun-provider.component';
import {GunTypeComponent} from './gun-type/gun-type.component';

const routes: Routes = [
  {path: 'clients', component: ClientsComponent},
  {path: 'clients-new', component: ClientNewComponent},
  {path: 'client/detail/:id', component: ClientDetailComponent},

  {path: 'gun-providers', component: GunProviderComponent},

  {path: 'gun-types', component: GunTypeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

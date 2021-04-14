import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from './clients/clients.component';

const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'clients', component: ClientsComponent},
  // {path: 'student/detail/:id', component: StudentDetailComponent},
  // {path: 'disciplines', component: DisciplinesComponent},
  // {path: 'discipline-new', component: DisciplineNewComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

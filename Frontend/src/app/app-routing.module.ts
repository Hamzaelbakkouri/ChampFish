import { ContainerComponent } from './Components/container/container.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionComponent } from './Components/competition/competition/competition.component';

const routes: Routes = [
  {
    path: '',
    component: ContainerComponent,
  },
  {
    path: 'competition',
    component: CompetitionComponent,
  },
  // {
  //   path: 'ranking',
  //   component: ,
  // },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

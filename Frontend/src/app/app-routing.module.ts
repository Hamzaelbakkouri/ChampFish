import { ContainerComponent } from './Components/container/container.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionComponent } from './Components/competition/competition/competition.component';
import { PodiumComponent } from './Components/podium/podium.component';
import { HuntingComponent } from './Components/Hunting/hunting/hunting.component';

const routes: Routes = [
  {
    path: '',
    component: ContainerComponent,
  },
  {
    path: 'competition',
    component: CompetitionComponent,
  },
  {
    path: 'podium/:code',
    component: PodiumComponent,
  },
  {
    path: 'hunting',
    component: HuntingComponent,
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

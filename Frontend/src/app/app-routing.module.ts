import { ContainerComponent } from './Components/container/container.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionComponent } from './Components/competition/competition/competition.component';
import { PodiumComponent } from './Components/podium/podium.component';
import { HuntingComponent } from './Components/Hunting/hunting/hunting.component';
import { SignInComponent } from './Components/auth/sign-in/sign-in.component';
import { LoggedInGuard } from './Guards/LoggedInGuard';
import { SignUpComponent } from './Components/auth/sign-up/sign-up.component';
import { IsAccountNoneGuard } from './Guards/IsAccoundNoneGuard';
import { ManagerAuthGuard } from './Guards/ManagerAuthGuard';
import { ManagerMembersComponent } from './Components/Manager/manager-members/manager-members.component';
import { AuthGuard } from './Guards/AuthGuard';
import { ErrorComponent } from './Errors/error/error.component';

const routes: Routes = [
  { path: '', component: ContainerComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'competition', component: CompetitionComponent, canActivate: [AuthGuard] },
  { path: 'member', component: ManagerMembersComponent },
  { path: 'podium/:code', component: PodiumComponent, canActivate: [AuthGuard] },
  { path: 'hunting', component: HuntingComponent },
  { path: 'login', component: SignInComponent, canActivate: [LoggedInGuard] },
  { path: 'signup', component: SignUpComponent, canActivate: [LoggedInGuard] },
  { path: 'managemember', component: ManagerMembersComponent, canActivate: [ManagerAuthGuard, IsAccountNoneGuard] }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

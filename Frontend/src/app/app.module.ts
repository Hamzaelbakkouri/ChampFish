import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CompetitionComponent } from './Components/competition/competition/competition.component';
import { CompetitionCreateComponent } from './Components/competition/competition-create/competition-create.component';
import { SidebarComponent } from './Components/sidebar/sidebar.component';
import { NgIconsModule } from '@ng-icons/core';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { heroHome, heroGlobeAlt, heroUserCircle, heroMap, heroUsers, heroTrash, heroBell, heroPlus, heroUserGroup, heroArchiveBoxXMark } from '@ng-icons/heroicons/outline';
import { ionFish , ionAdd} from '@ng-icons/ionicons'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ContainerComponent } from './Components/container/container.component'
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { CompetitionDeleteComponent } from './Components/competition/competition-delete/competition-delete.component';
import { RankingComponent } from './Components/ranking/ranking/ranking.component';
import { RankingCreateComponent } from './Components/ranking/ranking-create/ranking-create.component';
import { RankingDeleteComponent } from './Components/ranking/ranking-delete/ranking-delete.component';


@NgModule({
  declarations: [
    AppComponent,
    CompetitionComponent,
    CompetitionCreateComponent,
    SidebarComponent,
    ContainerComponent,
    CompetitionDeleteComponent,
    RankingComponent,
    RankingCreateComponent,
    RankingDeleteComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgIconsModule.withIcons({ heroHome, heroGlobeAlt, heroUserCircle, heroMap, heroUsers, heroTrash, heroBell, heroPlus, heroUserGroup, heroArchiveBoxXMark , ionFish , ionAdd}),
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

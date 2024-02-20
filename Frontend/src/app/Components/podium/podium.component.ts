import { ranking } from './../../Models/Ranking';
import { RankingService } from 'src/app/Services/ranking/ranking.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { Member } from 'src/app/Models/Member';

@Component({
  selector: 'app-podium',
  templateUrl: './podium.component.html',
  styleUrls: ['./podium.component.css']
})
export class PodiumComponent implements OnInit {

  ranking: any = []

  constructor(private rankingService: RankingService, private snackBar: MatSnackBar, private router: Router) { }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action)
  }

  ngOnInit(): void {
    this.rankingService.Count(this.router.url.split('/')[2]).subscribe(
      (data) => {
      },
      (error) => {
        catchError(error)
      }
    )

    this.rankingService.getRapport(this.router.url.split('/')[2]).subscribe(
      (data) => {
        this.ranking = data
        this.openSnackBar(`The Winner is ${this.ranking[0].id.member.name} 🥳🥳🚩`, "GG")
      },
      (error) => {
        catchError(error)
      }
    )
  }

}

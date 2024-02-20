import { ranking } from 'src/app/Models/Ranking';
import { Component, EventEmitter, Output, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {
  @Output() closeMediaPopup: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() closeAddingPopup: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() Members: any = {}
  @Input() competition_Code: string = ''
  isClosed: boolean = false;
  isHuntingClosed: boolean = false;
  memberNum: number = 0


  checkisClosed() {
    this.closeMediaPopup.emit(false);
  }


  openCreateRanking() {
    this.isClosed = true
  }

  CloseAdding() {
    this.isClosed = false
  }

  openHunting(member: number) {
    this.memberNum = member
    this.isHuntingClosed = true
  }

  closeHunting() {
    this.isHuntingClosed = false
  }

  ngOnInit(): void {
  }

}

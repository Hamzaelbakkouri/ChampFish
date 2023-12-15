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
  isClosed: boolean = false;

  checkisClosed() {
    this.closeMediaPopup.emit(false);
  }

  
  openCreateRanking() {
    this.isClosed = true
  }
  
  CloseAdding() {
    this.isClosed = false
  }
  
  ngOnInit(): void {
  }

}

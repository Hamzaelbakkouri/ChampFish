import { Component, EventEmitter } from '@angular/core';
import { competition } from 'src/app/Models/Competition';
import { CompetitionService } from 'src/app/Services/competition/competition.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject, catchError } from 'rxjs';
import { RankingService } from 'src/app/Services/ranking/ranking.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css']
})
export class CompetitionComponent {
  isCreatePopupOpen: boolean = false;
  isMemberPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;
  isPopupOpen: boolean = false;
  idxUpdate: number = 0;
  CompetitonList: any[] = [];
  idx: number = 0;
  idToDelete: string = '';
  deleteMessage: string = '';
  sendMediaData: EventEmitter<any> = new EventEmitter<any>();
  paginationSize: number = 0;
  currentPage = 0;
  Members: any[] = []
  itemToPaginate: number = 0;
  CompetitionCode: string = ''

  constructor(private competitionService: CompetitionService, private snackBar: MatSnackBar, private rankingService: RankingService, private router: Router) { }

  openCreatePopup() {
    this.isCreatePopupOpen = true;
  }


  filterCompettion(filter: string) {
    if (filter == "done") {
      this.competitionService.getDoneFilter(this.itemToPaginate, 20).subscribe((data: any) => {
        this.CompetitonList = []
        this.CompetitonList.push(...data.content);
        this.paginationSize = data.totalPages
      });
    } else if (filter == "inprogress") {
      this.competitionService.getInProgressFilter(this.itemToPaginate, 20).subscribe((data: any) => {
        this.CompetitonList = []
        this.CompetitonList.push(...data.content);
        this.paginationSize = data.totalPages
      });
    } else if (filter == "pending") {
      this.competitionService.getPendingFilter(this.itemToPaginate, 20).subscribe((data: any) => {
        this.CompetitonList = []
        this.CompetitonList.push(...data.content);
        this.paginationSize = data.totalPages
      });
    }

  }


  closeCreatePopup() {
    this.isCreatePopupOpen = false;
  }


  openPopup(id: any, index: number) {
    this.isPopupOpen = true;
    this.idToDelete = id;
    this.idx = index;
  }

  // sendDataMember = new Subject<any>
  openMembersPopUp(item: any, code : string) {
    this.CompetitionCode = code
    this.Members = item
    this.isMemberPopupOpen = true;
  }

  closeMembersPopUp() {
    this.isMemberPopupOpen = false;
  }

  closePopup() {
    this.isPopupOpen = false;
  }

  changePage(index: number): void {
    this.currentPage = index;
    this.pagination(index, 3)
    this.itemToPaginate = index
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2500,
    });
  }

  confirmDelete() {
    this.deleteCompetition(this.idToDelete, this.idx);
    this.isPopupOpen = false;
    this.openSnackBar("Competition deleted successfully", "Close");
  }

  ngOnInit(): void {
    this.pagination(0, 3);
    this.competitionService.sendcompetitionData.subscribe((data: any) => {
      // this.CompetitonList[this.idxUpdate] = data.competition;
      this.CompetitonList.push(data);
      this.closeCreatePopup();
      this.openSnackBar("Competition added successfully", "close");
    });
  }

  pagination(page: number, size: number) {
    this.competitionService.paginate(page, size).subscribe((data: any) => {
      this.CompetitonList = []
      this.CompetitonList.push(...data.content);
      this.paginationSize = data.totalPages
    });
  }

  deleteCompetition(id: any, idx: number) {
    this.competitionService.delete(id).subscribe((data: any) => {
      this.deleteMessage = data.message;
    });
    this.CompetitonList.splice(idx, 1);
  }

  isDateAllowed(date: any): string {
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    const day = today.getDate();

    if (year < date[0] || (year === date[0] && month < date[1]) || (year === date[0] && month === date[1] && day < date[2])) {
      return "inprogress";
    } else if (year === date[0] && month === date[1] && day === date[2]) {
      return "pending";
    } else if (year > date[0] || (year === date[0] && month > date[1]) || (year === date[0] && month === date[1] && day > date[2])) {
      return "done";
    }

    return ''
  }
}

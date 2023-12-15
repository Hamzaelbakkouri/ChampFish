import { Component, EventEmitter } from '@angular/core';
import { competition } from 'src/app/Models/Competition';
import { CompetitionService } from 'src/app/Services/competition/competition.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject } from 'rxjs';

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
  paginationSize: number[] = []
  currentPage = 0;
  Members: any[] = []

  constructor(private competitionService: CompetitionService, private snackBar: MatSnackBar) { }
  // create Subject popup
  openCreatePopup() {
    this.isCreatePopupOpen = true;
  }

  closeCreatePopup() {
    this.isCreatePopupOpen = false;
  }

  // delete Subject popup
  openPopup(id: any, index: number) {
    this.isPopupOpen = true;
    this.idToDelete = id;
    this.idx = index;
  }
  sendDataMember = new Subject<any>
  openMembersPopUp(item: any) {
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
    this.pagination(index, 5)
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
    this.competitionService.getAll().subscribe((data: any) => {
      this.CompetitonList = data.Competitions;


      this.paginationSize.push(data.totalPages)
    });

    this.competitionService.sendcompetitionData.subscribe((data: any) => {
      // this.CompetitonList[this.idxUpdate] = data.competition;
      this.CompetitonList.push(data);
      this.closeCreatePopup();
      this.openSnackBar("Competition added successfully", "close");
    });
  }

  pagination(page: number, size: number) {
    this.competitionService.paginate(page, size).subscribe((data: any) => {
      this.CompetitonList = data.content;
      this.paginationSize = data.totalPages
    });
  }

  deleteCompetition(id: any, idx: number) {
    this.competitionService.delete(id).subscribe((data: any) => {
      this.deleteMessage = data.message;
    });
    this.CompetitonList.splice(idx, 1);
  }

  isDateAllowed(date: any): boolean {
    const today = new Date();
    const year = today.getFullYear() < date[0];
    const month = today.getMonth() + 1 < date[1];
    const day = today.getDate() - 1 < date[2];
    if (year || month || day) {
      return true;
    }
    return false;
  }
}

import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-competition-delete',
  templateUrl: './competition-delete.component.html',
  styleUrls: ['./competition-delete.component.css']
})
export class CompetitionDeleteComponent {
  @Output() closePopup: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() deleteEvent = new EventEmitter<boolean>();

  delete() {
    this.deleteEvent.emit(true);
  }

  isClosed() {
    this.closePopup.emit(false);
  }

}

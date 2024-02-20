import { CompetitionService } from './../../../Services/competition/competition.service';

import { Component, EventEmitter, Output } from '@angular/core';
import { competition } from 'src/app/Models/Competition';


@Component({
  selector: 'app-competition-create',
  templateUrl: './competition-create.component.html',
  styleUrls: ['./competition-create.component.css']
})
export class CompetitionCreateComponent {
  @Output() closeCreatePopup: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() dataEvent = new EventEmitter<any>();
  require: string = "";
  currentDate = new Date();

  myDate: Date = new Date();
  StartDate: Date = new Date();
  EndDate: Date = new Date();
  Now: Date = new Date();
  new: any;
  CodeArray: string[] = []
  resultCode: string = ''
  constructor(private competitionService: CompetitionService) {
    this.myDate = new Date();
  }


  ngOnInit(): void {
  }

  checkisClosed() {
    this.closeCreatePopup.emit(false);
  }

  formData: competition = {
    code: null,
    date: null,
    startTime: null,
    endTime: null,
    numberOfParticipants: null,
    location: null,
    amount: null
  };

  onSubmit() {
    if (this.formData.code == null || this.formData.date == null || this.formData.startTime == null || this.formData.endTime == null || this.formData.amount == null || this.formData.numberOfParticipants == null || this.formData.location == null
    ) {
      this.require = "This Field is Required";
    } else if (this.formData.date <= new Date() || this.formData.startTime > this.formData.endTime) {
      this.require = "Date or Time must be greater than today";
    }
    this.generateCode();
    if (this.resultCode) {
      this.formData.code = this.resultCode;
    }
    this.CodeArray = [];
    this.createAnswer(this.formData);
  }

  generateCode() {
    this.CodeArray.push(this.formData.location!.split(' ').join('').substring(0, 3).toLowerCase())
    let arrayDate = this.formData.date!.toString().split('-')
    this.CodeArray.push(arrayDate[2])
    this.CodeArray.push(arrayDate[1])
    this.CodeArray.push(arrayDate[0].substring(2, 4))
    this.resultCode = this.CodeArray.join('-')
  }

  createAnswer(competition: competition): void {
    this.competitionService.save(competition).subscribe()
  }
}

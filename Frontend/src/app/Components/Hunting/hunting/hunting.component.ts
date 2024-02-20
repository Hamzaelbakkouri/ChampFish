import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { AutoCompleteCompleteEvent } from 'primeng/autocomplete';
import { Hunting } from 'src/app/Models/Hunting';
import { FishService } from 'src/app/Services/Fish/fish.service';
import { HuntingService } from 'src/app/Services/Hunting/hunting.service';

@Component({
  selector: 'app-hunting',
  templateUrl: './hunting.component.html',
  styleUrls: ['./hunting.component.css']
})
export class HuntingComponent implements OnInit {
  [x: string]: any;
  @Output() closeHuntingPopup: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() closeCreatingHunting: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() competition: string = ''
  @Input() memberNum: number = 0
  isClosed: boolean = false;
  Fish: any[] = []

  checkisClosed() {
    this.closeHuntingPopup.emit(false);
  }
  checkisHuntingClosed() {
    this.closeCreatingHunting.emit(false);
  }

  constructor(private fishService: FishService, private huntingService: HuntingService) { }

  formData: Hunting = {
    numberOfFish: null,
    fish_id: '',
    member_id: null,
    competition_id: ''
  }

  onSubmit() {
    this.formData.member_id = this.memberNum
    this.formData.competition_id = this.competition
    this.huntingService.saveHunting(this.formData).subscribe((data) => {
    })
  }

  ngOnInit(): void {
    this.fishService.getAllFish().subscribe((data: any) => {
      this.Fish.push(...data.Fishs)
    })



  }
}

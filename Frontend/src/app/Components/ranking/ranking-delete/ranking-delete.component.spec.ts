import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RankingDeleteComponent } from './ranking-delete.component';

describe('RankingDeleteComponent', () => {
  let component: RankingDeleteComponent;
  let fixture: ComponentFixture<RankingDeleteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RankingDeleteComponent]
    });
    fixture = TestBed.createComponent(RankingDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

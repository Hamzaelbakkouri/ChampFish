import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RankingCreateComponent } from './ranking-create.component';

describe('RankingCreateComponent', () => {
  let component: RankingCreateComponent;
  let fixture: ComponentFixture<RankingCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RankingCreateComponent]
    });
    fixture = TestBed.createComponent(RankingCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

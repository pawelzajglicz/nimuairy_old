import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConversationWindowHeaderComponent } from './conversation-window-header.component';

describe('ConversationWindowHeaderComponent', () => {
  let component: ConversationWindowHeaderComponent;
  let fixture: ComponentFixture<ConversationWindowHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConversationWindowHeaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConversationWindowHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

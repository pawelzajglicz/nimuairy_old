import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'nim-conversation-window-header',
  templateUrl: './conversation-window-header.component.html',
  styleUrls: ['./conversation-window-header.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConversationWindowHeaderComponent {

  @Input()  minimized!: boolean;
  @Output() minimizedChange = new EventEmitter<boolean>();
  @Output() closeEvent = new EventEmitter<void>();

  constructor() { }

  @Input() title: string;

  onClose() {
    this.closeEvent.emit();
  }

  maximize() {
    this.minimized = false;
    this.minimizedChange.emit(this.minimized);
  }

  minimize() {
    this.minimized = true;
    this.minimizedChange.emit(this.minimized);
  }
}

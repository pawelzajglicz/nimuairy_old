import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'nim-conversation-window-header',
  templateUrl: './conversation-window-header.component.html',
  styleUrls: ['./conversation-window-header.component.scss']
})
export class ConversationWindowHeaderComponent implements OnInit {

  constructor() { }

  @Input() title: string;

  ngOnInit(): void {
    console.log('asddd');
  }

}

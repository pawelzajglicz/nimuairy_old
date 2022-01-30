import { Pipe, PipeTransform } from '@angular/core';
import { AccountService } from 'src/app/services/account.service';
import { Conversation } from '../models/conversation';

@Pipe({
  name: 'extractOtherNames'
})
export class ExtractOtherNamesPipe implements PipeTransform {

  constructor(private accountService: AccountService) {
  }

  transform(conversation: Conversation): string {
    return conversation.participants
    .filter(participiant => participiant.username !== this.accountService.getCurrentUser().username)
    .map(participiant => participiant.username)
    .join(', ');
  }

}

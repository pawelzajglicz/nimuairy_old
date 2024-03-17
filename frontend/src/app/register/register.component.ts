import {Component, OnInit} from '@angular/core';
import {UntypedFormBuilder, Validators} from '@angular/forms';
import {AccountService} from '../services/account.service';
import {Validation} from '../util/validation';

@Component({
  selector: 'nim-register',
  templateUrl: './register.component.html',
  styleUrls: ['../styles/form-block.scss',
              './register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: any;
  showLoading: boolean;

  get f() { return this.registerForm.controls; }

  constructor(private accountsService: AccountService,
              private formBuilder: UntypedFormBuilder) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    }, {
      validators: [Validation.match('password', 'confirmPassword')]
    });
  }

  register() {
    if (this.registerForm.invalid) {
      return;
    }

    this.accountsService.register(this.registerForm.value);
  }
}

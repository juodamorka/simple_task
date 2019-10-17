import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.scss']
})
export class CreateCustomerComponent implements OnInit {

  registerForm: FormGroup;
  customer: Customer = new Customer();
  submitted = false;

  constructor(private customerService: CustomerService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.pattern('[A-Za-z.\'/()-]+')]],
      surname: ['', [Validators.required, Validators.minLength(2), Validators.pattern('[A-Za-z.\'/()-]+')]],
      birthDate: ['', [Validators.required, Validators.pattern('[0-9]{4}-[0-9]{2}-[0-9]{2}')]],
      phoneNumber: ['', [Validators.required, Validators.pattern('[0-9+-]+')]],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.save();
  }

  save() {
    console.log(this.customer);
    this.customerService.createCustomer(this.customer)
      .subscribe(data => console.log(data), error => console.log(error));
    this.newCustomer();
    this.gotoList();
  }

  newCustomer(): void {
    this.submitted = false;
    this.registerForm.reset();
    this.customer = new Customer();
  }

  gotoList() {
    this.router.navigate(['/customers']);
  }

}

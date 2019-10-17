import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/customer';

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:ban-types
  createCustomer(customer: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, customer);
  }

  getCustomerList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}

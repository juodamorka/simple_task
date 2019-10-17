package lt.juodamorka.simpletask.rest.service;

import lt.juodamorka.simpletask.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ValidationService {
    public boolean isInputValid(Customer customer) {
        return customer.getName() != null
                && customer.getSurname() != null
                && customer.getBirthDate() != null
                && isBirthDateValid(customer.getBirthDate())
                && customer.getPhoneNumber() != null
                && customer.getEmail() != null;
    }

    private boolean isBirthDateValid(Date birthDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return birthDate.before(calendar.getTime());
    }
}

package ru.academits.nekrasovgleb.servlet;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.converter.ContactConverter;
import ru.academits.nekrasovgleb.model.Contact;
import ru.academits.nekrasovgleb.service.ContactService;
import ru.academits.nekrasovgleb.service.ContactValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Contact contact = contactConverter.convertFormStringParam(contactParams);

        ContactValidation contactValidation = contactService.addContact(contact);
        contactService.saveLastContactValidation(contactValidation);

        if (contactValidation.isValid()) {
            contactService.saveLastContact(new Contact());
        } else {
            contactService.saveLastContact(contact);
        }

        resp.sendRedirect("/phonebook");
    }
}
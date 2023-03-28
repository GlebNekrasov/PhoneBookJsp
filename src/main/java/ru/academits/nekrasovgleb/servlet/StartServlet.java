package ru.academits.nekrasovgleb.servlet;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contactList", contactService.getAllContacts());
        req.setAttribute("contactValidation", contactService.getLastContactValidation());
        req.setAttribute("currentContact", contactService.getLastContact());
        req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
    }
}
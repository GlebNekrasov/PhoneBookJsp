package ru.academits.nekrasovgleb.servlet;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.converter.IdsConverter;
import ru.academits.nekrasovgleb.service.ContactService;
import ru.academits.nekrasovgleb.service.DeleteValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class DeleteContactsServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;

    private IdsConverter idsConverter = PhoneBook.idsConverter;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] checkedIds = req.getParameterValues("checked-id");

        String idsJson = Arrays.toString(checkedIds);

        int[] idsToDelete = idsConverter.convertFromJson(idsJson);

        DeleteValidation deleteValidation = contactService.deleteContacts(idsToDelete);

        if (!deleteValidation.isValid()) {
            resp.setStatus(500);
        }

        resp.sendRedirect("/phonebook");
    }
}
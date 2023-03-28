package ru.academits.nekrasovgleb;

import ru.academits.nekrasovgleb.converter.ContactConverter;
import ru.academits.nekrasovgleb.converter.IdsConverter;
import ru.academits.nekrasovgleb.dao.ContactDao;
import ru.academits.nekrasovgleb.service.ContactService;

public class PhoneBook {
    public static ContactDao contactDao = new ContactDao();

    public static ContactService contactService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static IdsConverter idsConverter = new IdsConverter();
}
package ru.academits.nekrasovgleb.service;

import ru.academits.nekrasovgleb.PhoneBook;
import ru.academits.nekrasovgleb.dao.ContactDao;
import ru.academits.nekrasovgleb.model.Contact;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class ContactService {
    private ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (StringUtils.isEmpty(contact.getFirstName())){
            contactValidation.setValid(false);
            contactValidation.setFirstNameError("Поле Имя должно быть заполнено.");
        }

        if (StringUtils.isEmpty(contact.getLastName())){
            contactValidation.setValid(false);
            contactValidation.setLastNameError("Поле Фамилия должно быть заполнено.");
        }

        if (StringUtils.isEmpty(contact.getPhone())){
            contactValidation.setValid(false);
            contactValidation.setPhoneError("Поле Телефон должно быть заполнено.");
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setGlobalError("Номер телефона не должен дублировать другие номера в телефонной книге.");
        }

        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }
        return contactValidation;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public DeleteValidation validateDelete(int[] ids) {
        DeleteValidation deleteValidation = new DeleteValidation();
        deleteValidation.setValid(true);

        if (ids.length == 0) {
            deleteValidation.setValid(false);
            deleteValidation.setError("Для удаления нужно передать хотя бы один id контакта.");
            return deleteValidation;
        }

        return deleteValidation;
    }

    public DeleteValidation deleteContacts(int[] idsToDelete) {
        DeleteValidation deleteValidation = validateDelete(idsToDelete);

        if (deleteValidation.isValid()) {
            contactDao.delete(idsToDelete);
        }

        return deleteValidation;
    }

    public void saveLastContact(Contact contact) {

        contactDao.saveLastContact(contact);
    }

    public Contact getLastContact() {
        return contactDao.getLastContact();
    }

    public void saveLastContactValidation(ContactValidation contactValidation) {
        contactDao.saveLastContactValidation(contactValidation);
    }

    public ContactValidation getLastContactValidation() {
        return contactDao.getLastContactValidation();
    }
}
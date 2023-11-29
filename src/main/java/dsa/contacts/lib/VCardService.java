package dsa.contacts.lib;

import dsa.contacts.model.Contact;

import dsa.contacts.model.Email;
import dsa.contacts.model.Phone;
import dsa.contacts.model.exceptions.PhoneException;
import ezvcard.VCard;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.StructuredName;

public class VCardService {
    private static VCardService instance;
    private VCardService() {}

    public static VCardService getInstance() {
        if (instance == null) {
            instance = new VCardService();
        }
        return instance;
    }

    public VCard contactToVcard(Contact contact) {
        VCard vcard = new VCard();

        StructuredName structuredName = new StructuredName();
        structuredName.setFamily(contact.getName());
        vcard.setStructuredName(structuredName);

        if (contact.getPhones() != null) {
            for (Phone phone : contact.getPhones()) {
                vcard.addTelephoneNumber(phone.getNum(), TelephoneType.get(phone.getPhoneType()));
            }
        }

        if (contact.getEmails() != null) {
            for (Email email : contact.getEmails()) {
                vcard.addEmail(email.getEmail(), EmailType.get(email.getEmailType()));
            }
        }
        return vcard;
    }

    public Contact vcardToContact(VCard vcard) {
        Contact contact = new Contact();

        contact.setNombre(vcard.getStructuredName().getFamily());

        return contact;
    }

    public static void main(String[] args) throws PhoneException {
        VCardService vcardService = VCardService.getInstance();
        Contact contact = new Contact("Pepe", new Phone("123456789", "Mobile", "34"));
        VCard vcard = vcardService.contactToVcard(contact);
        System.out.println(vcard);

        Contact contact2 = vcardService.vcardToContact(vcard);
        System.out.println(contact2);
    }
}

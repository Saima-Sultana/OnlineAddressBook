package net.therap.web;

import net.therap.domain.Contact;
import net.therap.domain.User;
import net.therap.service.ContactManager;
import org.junit.Assert;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import org.unitils.mock.annotation.Dummy;

import java.util.Arrays;
import java.util.List;

public class MyContactsControllerTest extends UnitilsTestNG {

    @TestedObject
    private MyContactsController myContactsController;

    @InjectIntoByType
    private Mock<ContactManager> contactManagerMock;

    protected List<Contact> contactList;

    @Dummy
    protected User user;

    @Dummy
    protected Contact contact1, contact2;

    @BeforeClass
    public void init() {
        contactList = Arrays.asList(contact1, contact2);
    }

    @Test
    public void testShowContacts() throws Exception {
        contactManagerMock.returns(contactList).getContacts(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ModelMap model = new ModelMap();

        request.setMethod("GET");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("User", user);
        request.setSession(session);

        String view1 = myContactsController.showContacts(model, request, response);
        Assert.assertEquals(view1, "mycontacts");
    }
}

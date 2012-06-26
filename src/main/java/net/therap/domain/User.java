package net.therap.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SAIMA_USER")
public class User {
    private long userId;
    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<Contact> contactList;
    private long version;

    public User() {
    }

    @Id
    @SequenceGenerator(name = "SAIMA_USER_SEQ", sequenceName = "SAIMA_USER_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SAIMA_USER_SEQ")
	@Column(name = "USER_ID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "LOGIN_NAME", nullable = false, unique = true)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "user",
        targetEntity = Contact.class,
        fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}

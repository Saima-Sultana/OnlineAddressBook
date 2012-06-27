package net.therap.domain;

import javax.persistence.*;

@Entity
@Table(name = "SAIMA_CONTACT")
public class Contact {
    private long contactId;
    private User user;
    private String firstName;
    private String lastName;
    private String formattedName;
    private String org;
    private String title;
    private String photoUrl;
    private String telWork;
    private String telHome;
    private String address;
    private String email;
    private String lastRevision;
    private long version;

    public Contact() {
    }

    @Id
    @SequenceGenerator(name = "SAIMA_CONTACT_SEQ", sequenceName = "SAIMA_CONTACT_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SAIMA_CONTACT_SEQ")
	@Column(name = "CONTACT_ID")
    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Column(name = "FORMATTED_NAME")
    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    @Column(name = "ORG")
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "PHOTO_URL")
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Column(name = "TEL_WORK", nullable = false)
    public String getTelWork() {
        return telWork;
    }

    public void setTelWork(String telWork) {
        this.telWork = telWork;
    }

    @Column(name = "TEL_HOME")
    public String getTelHome() {
        return telHome;
    }

    public void setTelHome(String telHome) {
        this.telHome = telHome;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "LAST_REVISION", nullable = false)
    public String getLastRevision() {
        return lastRevision;
    }

    public void setLastRevision(String lastRevision) {
        this.lastRevision = lastRevision;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}

package ru.stqa.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    ;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String phone;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmails;

    @Expose
    @Type(type = "text")
    @Column(name = "address")
    private String address;
    @Expose
    @Transient
    private String group;


    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    transient private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public int getId() {

        return id;

    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneHome() {

        return homephone;
    }

    public String getPhoneWork() {
        return workphone;
    }

    public String getPhone() {
        return phone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {

        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
    }


 /*   public File getPhoto() {
        return new File(photo);
    } */

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withPhoneHome(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withPhoneWork(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }


    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
            return this;
    }


   /* public ContactData withPhoto (File photo) {
        this.photo = photo.getPath();
        return this;
    }*/

     @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", homephone='" + homephone + '\'' +
                ", workphone='" + workphone + '\'' +
                ", phone='" + phone + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", groups=" + groups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(homephone, that.homephone) &&
                Objects.equals(workphone, that.workphone) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(allPhones, that.allPhones) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(allEmails, that.allEmails) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename, lastname, homephone, workphone, phone, allPhones, email, email2, email3, allEmails, address);
    }
}


package ru.stqa.pft.adressbook.model;

public class ContactData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String company;
    private final String address;
    private String group;


    public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
		this.group = group;
    }
    
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


    public String getCompany() {

        return company;
    }

    public String getAddress() {

        return address;
    }
    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}

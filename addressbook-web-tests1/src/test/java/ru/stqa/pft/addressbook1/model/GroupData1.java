package ru.stqa.pft.addressbook1.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_list")
@XStreamAlias("group")
public class GroupData1 {

  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private  int id = Integer.MAX_VALUE;;

  @Column(name = "group_name")
  private  String name;

  @Column(name = "group_header")
  @Type(type = "text")
  private  String header;

  @Column(name = "group_footer")
  @Type(type = "text")
  private  String footer;

  public GroupData1 withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData1 withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData1 withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public GroupData1 withId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @ManyToMany(mappedBy = "groups")
  private Set<ContactData> contacts = new HashSet<ContactData>();

  public Contact getContacts() {
    return new Contact(contacts);
  }

  @Override
  public String toString() {
    return "GroupData1{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData1 that = (GroupData1) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (header != null ? !header.equals(that.header) : that.header != null) return false;
    return footer != null ? footer.equals(that.footer) : that.footer == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (header != null ? header.hashCode() : 0);
    result = 31 * result + (footer != null ? footer.hashCode() : 0);
    return result;
  }
}

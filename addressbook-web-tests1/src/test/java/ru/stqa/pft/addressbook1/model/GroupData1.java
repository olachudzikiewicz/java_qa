package ru.stqa.pft.addressbook1.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("group")
public class GroupData1 {

  @XStreamOmitField
  private  int id = Integer.MAX_VALUE;;
  private  String name;
  private  String header;
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
    return name != null ? name.equals(that.name) : that.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}

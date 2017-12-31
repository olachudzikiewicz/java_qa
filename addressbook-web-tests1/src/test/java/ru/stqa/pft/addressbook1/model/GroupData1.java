package ru.stqa.pft.addressbook1.model;

public class GroupData1 {

  private  int id;
  private final String name;
  private final String header;
  private final String footer;

  public GroupData1(String name, String header, String footer) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

   public GroupData1(int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

    return name != null ? name.equals(that.name) : that.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}

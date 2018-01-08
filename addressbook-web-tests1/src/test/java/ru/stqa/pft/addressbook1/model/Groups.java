package ru.stqa.pft.addressbook1.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData1> {

  private Set<GroupData1> delegate;

  public Groups(Groups groups) {
  this.delegate = new HashSet<GroupData1>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData1>();
  }

  @Override
  protected Set<GroupData1> delegate() {
    return delegate;
  }

  public Groups withAdded (GroupData1 group) {
  Groups groups = new Groups(this);
  groups.add(group);
  return groups;
  }

  public Groups without (GroupData1 group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

}

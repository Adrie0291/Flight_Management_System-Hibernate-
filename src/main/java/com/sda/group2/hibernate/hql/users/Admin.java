package com.sda.group2.hibernate.hql.users;

import com.sda.group2.interfaces.UserOption;
import com.sda.group2.interfaces.options.AssistantCreation;
import com.sda.group2.interfaces.options.Logout;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account {

    public Admin() {
    }

    public Admin(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }

    @Override
    public List<UserOption> getOptions() {
        List<UserOption> list = new ArrayList<>();
        list.add(new AssistantCreation());
        list.add(new Logout());
        return list;
    }
}

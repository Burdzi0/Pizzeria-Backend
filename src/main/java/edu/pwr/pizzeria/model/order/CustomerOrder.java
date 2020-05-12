package edu.pwr.pizzeria.model.order;

import edu.pwr.pizzeria.model.user.CustomerUser;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
public abstract class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @ManyToOne
    protected CustomerUser customerUser;
    protected Date date;
    protected Time timePassed;
    protected String notes;

    public CustomerOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(CustomerUser customerUser) {
        this.customerUser = customerUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(Time timePassed) {
        this.timePassed = timePassed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOrder that = (CustomerOrder) o;

        return id == that.id &&
                customerUser.equals(that.customerUser) &&
                Objects.equals(date, that.date) &&
                Objects.equals(timePassed, that.timePassed) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerUser, date, timePassed, notes);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", customerUser=" + customerUser +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", notes='" + notes + '\'' +
                '}';
    }
}


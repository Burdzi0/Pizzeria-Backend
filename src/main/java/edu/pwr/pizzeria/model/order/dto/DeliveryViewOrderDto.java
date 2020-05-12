package edu.pwr.pizzeria.model.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pizzeria.model.order.delivery.DeliveryOrderStatus;
import edu.pwr.pizzeria.model.pizza.dto.CustomPizzaDto;
import edu.pwr.pizzeria.model.user.CustomerUser;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryViewOrderDto {

    private int id;
    private CustomerUser customerUser;
    private Date date;
    private Time timePassed;
    private String notes;
    private BigDecimal total;
    private DeliveryOrderStatus deliveryOrderStatus;

    @JsonCreator
    public DeliveryViewOrderDto(
            @JsonProperty("id") int id,
            @JsonProperty("customerUser") CustomerUser customerUser,
            @JsonProperty("date") Date date,
            @JsonProperty("timePassed") Time timePassed,
            @JsonProperty("notes") String notes,
            @JsonProperty("total") BigDecimal total,
            @JsonProperty("deliveryOrderStatus") DeliveryOrderStatus deliveryOrderStatus) {

        this.id = id;
        this.customerUser = customerUser;
        this.date = date;
        this.timePassed = timePassed;
        this.notes = notes;
        this.total = total;
        this.deliveryOrderStatus = deliveryOrderStatus;
    }

    public int getId() {
        return id;
    }

    public CustomerUser getCustomerUser() {
        return customerUser;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimePassed() {
        return timePassed;
    }

    public String getNotes() {
        return notes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public DeliveryOrderStatus getDeliveryOrderStatus() {
        return deliveryOrderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryViewOrderDto that = (DeliveryViewOrderDto) o;
        return id == that.id &&
                customerUser.equals(that.customerUser) &&
                Objects.equals(date, that.date) &&
                Objects.equals(timePassed, that.timePassed) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(total, that.total) &&
                deliveryOrderStatus == that.deliveryOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerUser, date, timePassed, notes, total, deliveryOrderStatus);
    }

    @Override
    public String toString() {
        return "DeliveryViewOrderDto{" +
                "id=" + id +
                ", customerUser=" + customerUser +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", notes='" + notes + '\'' +
                ", total=" + total +
                ", deliveryOrderStatus=" + deliveryOrderStatus +
                '}';
    }
}

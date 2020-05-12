package edu.pwr.pizzeria.model.order;

import java.math.BigDecimal;
import java.util.Objects;

public class DeliveryViewOrder extends CustomerOrder{

    private BigDecimal total;
    private DeliveryOrderStatus deliveryOrderStatus;

    public DeliveryViewOrder() {
        super();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public DeliveryOrderStatus getDeliveryOrderStatus() {
        return deliveryOrderStatus;
    }

    public void setDeliveryOrderStatus(DeliveryOrderStatus deliveryOrderStatus) {
        this.deliveryOrderStatus = deliveryOrderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeliveryViewOrder that = (DeliveryViewOrder) o;
        return Objects.equals(total, that.total) &&
                deliveryOrderStatus == that.deliveryOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), total, deliveryOrderStatus);
    }

    @Override
    public String toString() {
        return "DeliveryViewOrder{" +
                "total=" + total +
                ", deliveryOrderStatus=" + deliveryOrderStatus +
                ", id=" + id +
                ", customerUser=" + customerUser +
                ", date=" + date +
                ", timePassed=" + timePassed +
                ", notes='" + notes + '\'' +
                '}';
    }
}

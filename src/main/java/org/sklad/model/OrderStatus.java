package org.sklad.model;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    IN_CART,
    IN_PROCESS,
    READY_TO_DELIVER,
    BEING_DELIVERED,
    DELIVERED,
    CANCELED,
    WAITING_TO_ACCEPT
}

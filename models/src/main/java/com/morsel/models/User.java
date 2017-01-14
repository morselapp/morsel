package com.morsel.models;

import java.util.List;

/**
 * Created by ajeet on 14/1/17.
 */
public interface User {
    List<Order> getOrders();
    List<Order> getOrder();
    Address getAddress();
}

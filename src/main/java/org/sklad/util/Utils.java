package org.sklad.util;

import org.sklad.model.Order;
import org.sklad.model.OrderStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

abstract public class Utils {
    public static String getStatus(Order order){
        if (order.deliveryStatus == OrderStatus.READY_TO_DELIVER){
            return "Ready to Deliver";
        }
        if (order.deliveryStatus == OrderStatus.IN_PROCESS){
            return "In process";
        }
        if (order.deliveryStatus == OrderStatus.BEING_DELIVERED){
            return "Coming Soon";
        }
        if (order.deliveryStatus == OrderStatus.DELIVERED){
            return "Delivered";
        }
        if (order.deliveryStatus == OrderStatus.CANCELED){
            return "Canceled";
        }
        return "N/A";
    }

    public static String getCurrentDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public static boolean isItToday(String date){
        if (Objects.equals(date, getCurrentDate())){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDateFormat(String dateStr){
        String DEFAULT_PATTERN = "dd/MM/yyyy";
        Date date;
        try {
            DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        if (isItToday(dateStr)){
            return true;
        }
        return date.after(new Date());
    }
}

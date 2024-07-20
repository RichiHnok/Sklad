package org.sklad.util;

import org.sklad.model.ClientOrder;
import org.sklad.model.OrderStatus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

abstract public class Utils {
    public static String getStatus(ClientOrder clientOrder) {
        if (clientOrder.deliveryStatus == OrderStatus.READY_TO_DELIVER) {
            return "Ready to Deliver";
        }
        if (clientOrder.deliveryStatus == OrderStatus.IN_PROCESS) {
            return "In process";
        }
        if (clientOrder.deliveryStatus == OrderStatus.BEING_DELIVERED) {
            return "Coming Soon";
        }
        if (clientOrder.deliveryStatus == OrderStatus.DELIVERED) {
            return "Delivered";
        }
        if (clientOrder.deliveryStatus == OrderStatus.CANCELED) {
            return "Canceled";
        }
        return "N/A";
    }

    public static String getStatus(OrderStatus status) {
        if (status == OrderStatus.READY_TO_DELIVER) {
            return "Ready to Deliver";
        }
        if (status == OrderStatus.IN_PROCESS) {
            return "In process";
        }
        if (status == OrderStatus.BEING_DELIVERED) {
            return "Coming Soon";
        }
        if (status == OrderStatus.DELIVERED) {
            return "Delivered";
        }
        if (status == OrderStatus.CANCELED) {
            return "Canceled";
        }
        if (status == OrderStatus.WAITING_TO_ACCEPT) {
            return "Ready to Pick Up";
        }
        return "N/A";
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public static boolean isItToday(String date) {
        if (Objects.equals(date, getCurrentDate())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDateFormat(String dateStr) {
        String DEFAULT_PATTERN = "dd/MM/yyyy";
        Date date;
        try {
            DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        if (isItToday(dateStr)) {
            return true;
        }
        return date.after(new Date());
    }

    public static Image resizeImage(URL imageUrl) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageUrl);
        Image scaledImage = originalImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        BufferedImage bufferedScaledImage = new BufferedImage(75, 75, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedScaledImage.createGraphics();
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();
        return bufferedScaledImage;
    }
}

package teste2;

import java.util.*;
import java.text.SimpleDateFormat;

public class Service {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) return;
        }
        rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
    }

    public void setUser(int userId, int balance) {
        for (User u : users) {
            if (u.userId == userId) return;
        }
        users.add(new User(userId, balance));
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        if (!checkIn.before(checkOut)) {
            System.out.println("Invalid booking period.");
            return;
        }

        Room room = null;
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                room = r;
                break;
            }
        }
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        User user = null;
        for (User u : users) {
            if (u.userId == userId) {
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        for (Booking b : bookings) {
            if (b.roomNumber == roomNumber &&
                !(checkOut.before(b.checkIn) || checkIn.after(b.checkOut))) {
                System.out.println("Room is not available.");
                return;
            }
        }

        long nights = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
        int totalCost = (int) nights * room.pricePerNight;

        if (user.balance < totalCost) {
            System.out.println("Not enough balance.");
            return;
        }

        user.balance -= totalCost;
        bookings.add(new Booking(user, room, checkIn, checkOut, totalCost));
    }

    public void printAllUsers() {
        ListIterator<User> it = users.listIterator(users.size());
        while (it.hasPrevious()) {
            User u = it.previous();
            System.out.println("User ID: " + u.userId + ", Balance: " + u.balance);
        }
    }

    public void printAll() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ListIterator<Room> rit = rooms.listIterator(rooms.size());
        while (rit.hasPrevious()) {
            Room r = rit.previous();
            System.out.println("Room #" + r.roomNumber + " | Type: " + r.roomType + " | Price: " + r.pricePerNight);
        }

        ListIterator<Booking> bit = bookings.listIterator(bookings.size());
        while (bit.hasPrevious()) {
            Booking b = bit.previous();
            System.out.println("Booking - Room #" + b.roomNumber + ", Type: " + b.roomType +
                    ", User ID: " + b.userId + ", From: " + sdf.format(b.checkIn) + " To: " + sdf.format(b.checkOut) +
                    ", Price: " + b.totalPrice + ", User Original Balance: " + b.originalUserBalance);
        }
    }
}


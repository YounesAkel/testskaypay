package teste2;

import java.util.Date;

public class Booking {
    public int userId;
    public int roomNumber;
    public RoomType roomType;
    public int roomPrice;
    public int totalPrice;
    public Date checkIn;
    public Date checkOut;
    public int originalUserBalance;

    public Booking(User user, Room room, Date checkIn, Date checkOut, int totalPrice) {
        this.userId = user.userId;
        this.roomNumber = room.roomNumber;
        this.roomType = room.roomType;
        this.roomPrice = room.pricePerNight;
        this.originalUserBalance = user.balance + totalPrice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }
}


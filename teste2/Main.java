package teste2;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        Service service = new Service();

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"));
        service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"));
        service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026"));
        service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

        service.setRoom(1, RoomType.SUITE, 10000); // Should not affect existing bookings

        System.out.println("\n--- All Users ---");
        service.printAllUsers();

        System.out.println("\n--- All Bookings and Rooms ---");
        service.printAll();
    }
}


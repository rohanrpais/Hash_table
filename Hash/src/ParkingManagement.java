import java.util.*;

public class ParkingManagement {

        private String[] spots;
        private int size;

        public ParkingManagement(int size) {
            this.size = size;
            spots = new String[size];
        }

        private int hash(String plate) {
            return Math.abs(plate.hashCode()) % size;
        }

        public int parkVehicle(String plate) {
            int idx = hash(plate);
            int probes = 0;
            while (spots[idx] != null) {
                idx = (idx + 1) % size;
                probes++;
            }
            spots[idx] = plate;
            System.out.println("Parked " + plate + " at spot " + idx + " (probes: " + probes + ")");
            return idx;
        }

        public void exitVehicle(String plate) {
            for (int i = 0; i < size; i++) {
                if (plate.equals(spots[i])) {
                    spots[i] = null;
                    System.out.println("Exited " + plate + " from spot " + i);
                    break;
                }
            }
        }


    public static void main(String[] args) {
        ParkingManagement lot = new ParkingManagement(5);
        lot.parkVehicle("ABC-123");
        lot.parkVehicle("XYZ-999");
        lot.exitVehicle("ABC-123");
    }
}

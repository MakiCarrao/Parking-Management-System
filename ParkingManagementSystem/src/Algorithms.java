public class Algorithms {
    public static Vehicle linearSearch(Vehicle[] arr, int count, String query) {
        String lower = query.toLowerCase();
        for (int i = 0; i < count; i++) {
            Vehicle v = arr[i];
            if (v == null) {
                continue;
            }
            boolean plateMatch = v.getPlateNumber().toLowerCase().contains(lower);
            boolean ownerMatch = v.getOwnerName().toLowerCase().contains(lower);
            if (plateMatch || ownerMatch) {
                return v;
            }
        }
        return null;
    }
    public static Vehicle binarySearch(Vehicle[] arr, int count, String plate) {
        int low = 0;
        int high = count - 1;
        String target = plate.toUpperCase();
        while (low <= high) {
            int mid = (low + high) / 2;
            String midPlate = arr[mid].getPlateNumber().toUpperCase();
            int cmp = midPlate.compareTo(target);
            if (cmp == 0) {
                return arr[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
    public static void insertionSort(Vehicle[] arr, int count, String key) {
        for (int i = 1; i < count; i++) {
            Vehicle current = arr[i];
            int j = i - 1;
            while (j >= 0 && comesAfter(arr[j], current, key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }
    private static boolean comesAfter(Vehicle a, Vehicle b, String key) {
        if (key.equals("Plate Number")) {
            return a.getPlateNumber().compareToIgnoreCase(b.getPlateNumber()) > 0;
        }
        if (key.equals("Time In")) {
            return a.getTimeInMillis() > b.getTimeInMillis();
        }
        return a.getVehicleType().compareToIgnoreCase(b.getVehicleType()) > 0;
    }
}
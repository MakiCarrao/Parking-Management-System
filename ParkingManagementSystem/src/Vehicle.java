import java.text.SimpleDateFormat;
import java.util.Date;
public class Vehicle {
    private String plateNumber;
    private String ownerName;
    private String vehicleType;
    private String timeIn;
    private long timeInMillis;
    private int slotNumber;
    public Vehicle(String plateNumber, String ownerName, String vehicleType, int slotNumber) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
        recordTimeIn();
    }
    public void recordTimeIn() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        this.timeIn = format.format(new Date());
        this.timeInMillis = System.currentTimeMillis();
    }
    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
        this.timeInMillis = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            this.timeInMillis = format.parse(timeIn).getTime();
        } catch (Exception ex) {
        }
    }
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public String getTimeIn() {
        return timeIn;
    }
    public long getTimeInMillis() {
        return timeInMillis;
    }
    public int getSlotNumber() {
        return slotNumber;
    }
}
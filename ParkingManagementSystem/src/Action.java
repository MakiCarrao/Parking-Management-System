public class Action {
    private String type;
    private Vehicle vehicle;
    private int slotNumber;
    public Action(String type, Vehicle vehicle, int slotNumber) {
        this.type = type;
        this.vehicle = vehicle;
        this.slotNumber = slotNumber;
    }
    public String getType() {
        return type;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public int getSlotNumber() {
        return slotNumber;
    }
}
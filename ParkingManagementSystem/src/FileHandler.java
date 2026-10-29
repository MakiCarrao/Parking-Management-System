import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
public class FileHandler {
    public static boolean save(Vehicle[] slots, MyQueue queue, MyStack history, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < slots.length; i++) {
                if (slots[i] != null) {
                    Vehicle v = slots[i];
                    writer.write("PARKED|" + v.getSlotNumber() + "|" + v.getPlateNumber()
                            + "|" + v.getOwnerName() + "|" + v.getVehicleType() + "|" + v.getTimeIn());
                    writer.newLine();
                }
            }
            Vehicle[] queued = queue.toArray();
            for (int i = 0; i < queued.length; i++) {
                writer.write("QUEUE|" + queued[i].getPlateNumber()
                        + "|" + queued[i].getOwnerName() + "|" + queued[i].getVehicleType());
                writer.newLine();
            }
            Action[] actions = history.toArray();
            for (int i = 0; i < actions.length; i++) {
                Vehicle v = actions[i].getVehicle();
                writer.write("HISTORY|" + actions[i].getType() + "|" + actions[i].getSlotNumber()
                        + "|" + v.getPlateNumber() + "|" + v.getOwnerName()
                        + "|" + v.getVehicleType() + "|" + v.getTimeIn());
                writer.newLine();
            }
            writer.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public static boolean load(Vehicle[] slots, MyQueue queue, MyStack history, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (int i = 0; i < slots.length; i++) {
                slots[i] = null;
            }
            queue.clear();
            history.clear();
            Action[] historyItems = new Action[1000];
            int historyCount = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals("PARKED") && parts.length >= 6) {
                    int slot = Integer.parseInt(parts[1]);
                    Vehicle v = new Vehicle(parts[2], parts[3], parts[4], slot);
                    v.setTimeIn(parts[5]);
                    slots[slot - 1] = v;
                } else if (parts[0].equals("QUEUE") && parts.length >= 4) {
                    Vehicle v = new Vehicle(parts[1], parts[2], parts[3], 0);
                    queue.enqueue(v);
                } else if (parts[0].equals("HISTORY") && parts.length >= 7 && historyCount < 1000) {
                    int slot = Integer.parseInt(parts[2]);
                    Vehicle v = new Vehicle(parts[3], parts[4], parts[5], slot);
                    v.setTimeIn(parts[6]);
                    historyItems[historyCount] = new Action(parts[1], v, slot);
                    historyCount++;
                }
                line = reader.readLine();
            }
            reader.close();
            for (int i = historyCount - 1; i >= 0; i--) {
                history.push(historyItems[i]);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
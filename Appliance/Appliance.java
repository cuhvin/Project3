import java.util.Random;

class Appliance {
    private int locationID;
    private String appName;
    private int onPower;
    private float probOn;
    private int applianceID;

    public Appliance(int locationID, String appName, int onPower, float probOn) {
        this.locationID = locationID;
        this.appName = appName;
        this.onPower = onPower;
        this.probOn = probOn;
        this.applianceID = generateApplianceID();
    }

    public int getLocationID() {
        return locationID;
    }

    public String getAppName() {
        return appName;
    }

    public int getOnPower() {
        return onPower;
    }

    public float getProbOn() {
        return probOn;
    }

    public int getApplianceID() {
        return applianceID;
    }

    public boolean isTurnedOn() {
        // Implement logic to determine if the appliance is turned on based on probability
        return new Random().nextFloat() <= probOn;
    }

    private int generateApplianceID() {
        // Implement logic to generate a unique appliance ID
        return new Random().nextInt(1000000) + 10000000;
    }

    @Override
    public String toString() {
        return "Appliance{" +
                "locationID=" + locationID +
                ", appName='" + appName + '\'' +
                ", onPower=" + onPower +
                ", probOn=" + probOn +
                ", applianceID=" + applianceID +
                '}';
    }
}

class RegularAppliance extends Appliance {
    public RegularAppliance(int locationID, String appName, int onPower, float probOn) {
        super(locationID, appName, onPower, probOn);
    }

    @Override
    public boolean isTurnedOn() {
        // Regular appliances have a straightforward on/off state
        return super.isTurnedOn();
    }
}

class SmartAppliance extends Appliance {
    private double lowPower;

    public SmartAppliance(int locationID, String appName, int onPower, double lowPower, float probOn) {
        super(locationID, appName, onPower, probOn);
        this.lowPower = lowPower;
    }

    public double getLowPower() {
        return lowPower;
    }

    @Override
    public String toString() {
        return "SmartAppliance{" +
                "locationID=" + getLocationID() +
                ", appName='" + getAppName() + '\'' +
                ", onPower=" + getOnPower() +
                ", lowPower=" + lowPower +
                ", probOn=" + getProbOn() +
                ", applianceID=" + getApplianceID() +
                '}';
    }
}

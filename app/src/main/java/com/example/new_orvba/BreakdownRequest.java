package com.example.new_orvba;

public class BreakdownRequest {
    private String userId;
    private String vehicleBrand;
    private String vehicleName;
    private String serviceName;
    private boolean tyreService;
    private boolean fuelDelivery;
    private boolean carTowing;
    private boolean oilLeakage;
    private boolean mechanicalFaults;
    private boolean carRepairing;
    private boolean batteryJumpStart;
    private boolean accidentalRepair;
    private String defaultTrouble;


    // Updated constructor parameters to include vehicleBrand, vehicleName, serviceName
    public BreakdownRequest(String userId, String vehicleBrand, String vehicleName, String serviceName,
                            boolean tyreService, boolean fuelDelivery, boolean carTowing,
                            boolean oilLeakage, boolean mechanicalFaults, boolean carRepairing,
                            boolean batteryJumpStart, boolean accidentalRepair,
                            String defaultTrouble) {
        this.userId = userId;
        this.vehicleBrand = vehicleBrand;
        this.vehicleName = vehicleName;
        this.serviceName = serviceName;
        this.tyreService = tyreService;
        this.fuelDelivery = fuelDelivery;
        this.carTowing = carTowing;
        this.oilLeakage = oilLeakage;
        this.mechanicalFaults = mechanicalFaults;
        this.carRepairing = carRepairing;
        this.batteryJumpStart = batteryJumpStart;
        this.accidentalRepair = accidentalRepair;
        this.defaultTrouble = defaultTrouble;
    }

    // Add getters and setters as needed

    // Example getter
    public String getUserId() {
        return userId;
    }

    // Example setter
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Add other getters and setters for the remaining attributes
}


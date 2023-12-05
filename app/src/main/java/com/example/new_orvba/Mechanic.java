package com.example.new_orvba;

public class Mechanic {
    public String mechanicid;
    public String mfirstName;
    public String mlastName;
    public String memail;
    public String mphone;
    public String maddress;
    public boolean tyreServices;
    public boolean engineServices;
    public boolean oilLeakage;
    public boolean carTowing;
    public boolean manualRepairing;
    public boolean other;

    // Default constructor required for calls to DataSnapshot.getValue(Mechanic.class)
    public Mechanic(String uid, String email, String firstName, String lastName) {
    }

    public Mechanic(String mechanicId, String firstName, String lastName, String email, String phone, String address,
                    boolean tyreServices, boolean engineServices, boolean oilLeakage,
                    boolean carTowing, boolean manualRepairing, boolean other) {
        this.mechanicid = mechanicId;
        this.mfirstName = firstName;
        this.mlastName = lastName;
        this.memail = email;
        this.mphone = phone;
        this.maddress = address;
        this.tyreServices = tyreServices;
        this.engineServices = engineServices;
        this.oilLeakage = oilLeakage;
        this.carTowing = carTowing;
        this.manualRepairing = manualRepairing;
        this.other = other;
    }
}

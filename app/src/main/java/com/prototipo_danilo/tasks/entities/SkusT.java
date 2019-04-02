package com.prototipo_danilo.tasks.entities;

import java.util.ArrayList;

public class SkusT {
    private String Id;
    private String Name;
    public ArrayList< Object > Sellers = new ArrayList < Object > ();
    public ArrayList < Object > Images = new ArrayList < Object > ();
    VariationsT VariationsObject;
    private String SkuName;
    private float UnitMultiplier;
    private String ComplementName;
    private String MeasurementUnit;
    public ArrayList < Object > ReferenceId = new ArrayList < Object > ();
    private String EAN;


    // Getter Methods

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public VariationsT getVariations() {
        return VariationsObject;
    }

    public String getSkuName() {
        return SkuName;
    }

    public float getUnitMultiplier() {
        return UnitMultiplier;
    }

    public String getComplementName() {
        return ComplementName;
    }

    public String getMeasurementUnit() {
        return MeasurementUnit;
    }

    public String getEAN() {
        return EAN;
    }

    // Setter Methods

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setVariations(VariationsT VariationsObject) {
        this.VariationsObject = VariationsObject;
    }

    public void setSkuName(String SkuName) {
        this.SkuName = SkuName;
    }

    public void setUnitMultiplier(float UnitMultiplier) {
        this.UnitMultiplier = UnitMultiplier;
    }

    public void setComplementName(String ComplementName) {
        this.ComplementName = ComplementName;
    }

    public void setMeasurementUnit(String MeasurementUnit) {
        this.MeasurementUnit = MeasurementUnit;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }
}

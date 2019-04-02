package com.prototipo_danilo.tasks.entities;

import java.util.ArrayList;

public class ProductsT {
    private boolean Availability;
    public ArrayList < Object > Skus = new ArrayList < Object > ();
    private String Name;
    private String Id;
    private String Brand;
    private String Description;
    private String Category;
    public ArrayList < Object > Categories = new ArrayList < Object > ();
    SpecificationsT SpecificationsObject;
    public ArrayList < Object > Variations = new ArrayList < Object > ();
    private String RealId;


    // Getter Methods

    public boolean getAvailability() {
        return Availability;
    }

    public String getName() {
        return Name;
    }

    public String getId() {
        return Id;
    }

    public String getBrand() {
        return Brand;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategory() {
        return Category;
    }

    public SpecificationsT getSpecifications() {
        return SpecificationsObject;
    }

    public String getRealId() {
        return RealId;
    }

    // Setter Methods

    public void setAvailability(boolean Availability) {
        this.Availability = Availability;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setSpecifications(SpecificationsT SpecificationsObject) {
        this.SpecificationsObject = SpecificationsObject;
    }

    public void setRealId(String RealId) {
        this.RealId = RealId;
    }
}


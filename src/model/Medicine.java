package model;

import javafx.scene.control.CheckBox;

public abstract class Medicine {
    private int articleNo;
    private boolean onPrescription;
    private String name;
    private String producer;
    private String packageSize;
    private String description;
    private int quantity;
    private int quantityReserved;
    private double price;
    private String searchTerms;
    private int groupId;
    private boolean isActive;
    private CheckBox checkBox;

    public Medicine(int articleNo, int groupId, boolean onPrescription, String name, String producer, String packageSize, String description, int quantity, double price, String searchTerms, boolean isActive){
        setArticleNo(articleNo);
        setName(name);
        setProducer(producer);
        setPackageSize(packageSize);
        setDescription(description);
        setQuantity(quantity);
        setPrice(price);
        setSearchTerms(searchTerms);
        setGroup(groupId);
        setOnPrescription(onPrescription);
        setActive(isActive);
        setCheckBox(new CheckBox());
    }

    public Medicine() {
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public void setOnPrescription(boolean onPrescription) {
        this.onPrescription = onPrescription;
    }

    public void setName(String name) {
        if (name.length() > 45) {
            this.name = name.substring(0, 44);
        } else this.name = name;
    }

    public void setProducer(String producer) {
        if (producer.length() > 45) {
            this.producer = producer.substring(0, 44);
        } else this.producer = producer;
    }

    public void setPackageSize(String packageSize) {
        if (packageSize.length() > 45) {
            this.packageSize = packageSize.substring(0, 44);
        } else this.packageSize = packageSize;
    }

    public void setDescription(String description) {
        if (description.length() > 250) {
            this.description = description.substring(0, 249);
        } else this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSearchTerms(String searchTerms) {
        if (searchTerms.length() > 256) {
            this.searchTerms = searchTerms.substring(0, 255);
        } else this.searchTerms = searchTerms;
    }

    public void setGroup(int groupId) {
        this.groupId = groupId;
    }

    public void setQuantityReserved(int quantityReserved) {
        this.quantityReserved = quantityReserved;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public boolean isOnPrescription() {
        return onPrescription;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public int getGroup() {
        return groupId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public int getQuantityReserved() {
        return quantityReserved;
    }


    @Override
    public String toString() {
        return "Medicine{" +
                "articleNo=" + getArticleNo() +
                ", groupId=" + getGroup() +
                ", onPrescription=" + isOnPrescription() +
                ", name='" + getName() + '\'' +
                ", producer='" + getProducer() + '\'' +
                ", packageSize='" + getPackageSize() + '\'' +
                ", quantity=" + getQuantity() +
                ", reserved=" + getQuantityReserved() +
                ", price=" + getPrice() +
                ", searchTerms='" + getSearchTerms() + '\'' +
                ", isActive=" + getActive() +
                '}';
    }
}

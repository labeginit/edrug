package model;


public abstract class Medicine {
    private int articleNo;
    private boolean onPrescription;
    private String name;
    private String producer;
    private String packageSize;
    private String description;
    private int quantity;
    private double price;
    private String searchTerms;
    private int groupId;
    private boolean isActive;

    public Medicine(){}

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
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public void setOnPrescription(boolean onPrescription) {
        this.onPrescription = onPrescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSearchTerms(String searchTerms) {
       this.searchTerms = searchTerms;
    }

    public void setGroup(int group) {
        this.groupId = groupId;
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

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "articleNo=" + articleNo +
                ", onPrescription=" + onPrescription +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", packageSize='" + packageSize + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", searchTerms='" + searchTerms + '\'' +
                ", groupId=" + groupId +
                ", isActive=" + isActive +
                '}';
    }
}

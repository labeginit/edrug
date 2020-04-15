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

    public Medicine(){}

    public Medicine(int articleNo, boolean onPrescription, String name, String producer, String packageSize, String description, int quantity, double price, String searchTerms, int groupId){
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

    @Override
    public String toString() {
        return "Medicine{" +
                "articleNo=" + getArticleNo() +
                ", name='" + getName() + '\'' +
                ", producer='" + getProducer() + '\'' +
                ", packageSize='" + getPackageSize() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", quantity='" + getQuantity() + '\'' +
                ", price='" + getPrice() + '\'' +
                ", activeIngredients=" + getSearchTerms() + ", ";
    }
}

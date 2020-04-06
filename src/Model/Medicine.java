package Model;

import java.util.Arrays;

public abstract class Medicine {
    private int articleNo;
    private String name;
    private String producer;
    private String packageSize;
    private String description;
    private String[] activeIngredients;

    public Medicine(){}

    public Medicine(int articleNo, String name, String producer, String packageSize, String description, String[] activeIngredients){
        setArticleNo(articleNo);
        setName(name);
        setProducer(producer);
        setPackageSize(packageSize);
        setDescription(description);
        setActiveIngredients(activeIngredients);
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
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

    public void setActiveIngredients(String[] activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public int getArticleNo() {
        return articleNo;
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

    public String[] getActiveIngredients() {
        return activeIngredients;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "articleNo=" + getArticleNo() +
                ", name='" + getName() + '\'' +
                ", producer='" + getProducer() + '\'' +
                ", packageSize='" + getPackageSize() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", activeIngredients=" + Arrays.toString(getActiveIngredients()) + ", ";
    }
}

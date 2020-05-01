package model;

import model.dBConnection.DAOMedicine;

import java.util.List;

public class ProdGroup {
    private int id;
    private String name;
    private String path;
    public DAOMedicine dbMedicine = new DAOMedicine();

    public ProdGroup(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath(){
        return path;
    }

    public List<ProdGroup> getGroupList() {
        return dbMedicine.retrieveProductGroupList();
    }

    public ProdGroup getProdGroup(int id){
        return dbMedicine.retrieveProductGroup(id);
    }

    @Override
    public String toString() {
        return "ProdGroup{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", path='" + getPath() + '\'' +
                '}';
    }
}

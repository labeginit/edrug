package model;

import model.dBConnection.DAOMedicine;

import java.util.ArrayList;
import java.util.List;

public class ProdGroup {
    private int id;
    private String name;
    private String path;
    DAOMedicine dbMedicine = new DAOMedicine();
    List<String> groups = new ArrayList<>();

    public ProdGroup(){}

    public ProdGroup(int id, String name, String path) {
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

    public List<String> getPathList() {
        return dbMedicine.retrieveProductGroupList();
    }

    public ProdGroup getProdGroup(int id){
        return dbMedicine.retrieveProductGroup(id);
    }

}

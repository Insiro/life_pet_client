package com.insiro.lifepet.pet;
import com.insiro.lifepet.entity.Pet;
public class pet_data {
    private String petCategory;
    private String petName;
    private int id;
    private int intimacy;
    private double exp;
    private int level;
    public pet_data(int id,String name, String petCategory, int intimacy, double exp, int level){
        this.id=id;
        this.petName=name;
        this.petCategory=petCategory;
        this.intimacy=intimacy;
        this.exp=exp;
        this.level=level;


    }
    public int getId(){
        return this.id;
    }
    public String getPetName() {
        return petName;
    }
    public String getPetCategory(){
        return this.petCategory;
    }

    public int getIntimacy() {
        return this.intimacy;
    }

    public double getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}
package com.insiro.lifepet.pet;
import com.insiro.lifepet.entity.Pet;
public class pet_data {
    private String petCategory;
    private int id;
    private int intimacy;
    private float exp;
    private int level;
    public pet_data(int id, String petCategory, int intimacy, float exp, int level){
        this.id=id;
        this.petCategory=petCategory;
        this.intimacy=intimacy;
        this.exp=exp;
        this.level=level;


    }
    public int getId(){
        return this.id;
    }
    public String getPetCategory(){
        return this.petCategory;
    }

    public int getIntimacy() {
        return this.intimacy;
    }

    public float getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}
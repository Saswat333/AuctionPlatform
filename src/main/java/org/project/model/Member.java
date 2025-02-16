package org.project.model;

public class Member {
    private int id;
    private String name;
    private int superCoins;

    public Member(int id, String name, int superCoins) {
        this.id = id;
        this.name = name;
        this.superCoins = superCoins;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSuperCoins() {
        return superCoins;
    }

    public void setSuperCoins(int superCoins) {
        this.superCoins = superCoins;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", superCoins=" + superCoins +
                '}';
    }
}

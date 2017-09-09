package com.example.minesweeper;

/**
 * Created by or yossipof on 05/09/2017.
 */

public class User
{
    private String name;
    private int time;
    private int id;

    public User(String name, int time, int id)
    {
        this.name = name;
        this.time = time;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setID(int id) {
        this.id = id;
    }





}

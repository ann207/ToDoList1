package com.rsc.annaparrish.todolist;


import android.widget.Toast;

public class Item {
    private int _id;
    private String ToDo;

    public Item(){

    }
    public Item(String ToDo){
        this.ToDo = ToDo;
    }
    public int getList(){
        return _id;
    }
    public void setList(int _id){
        this._id = _id;
    }
    public String getToDo(){
        return ToDo;
    }
    public void setToDo(String ToDo){
        this.ToDo = ToDo;
    }

}

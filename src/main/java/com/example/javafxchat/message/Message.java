package com.example.javafxchat.message;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    public String name;
    private MessageType type;
    private String msg;
    private int count;
    private ArrayList<User> list;
    private ArrayList<User> users;

    private Status status;

}

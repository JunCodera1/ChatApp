package com.example.javafxchat.model.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Message implements Serializable {
    public String name;
    private MessageType type;
    private String msg;
    private int count;
    private String picture;
    private ArrayList<User> list;
    private ArrayList<User> users;

    private Status status;
    private byte[] voiceMsg;
    private byte[]  getVoiceMsg(){return voiceMsg;}

    public String getPicture(){return picture;}

    public Message(){}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getMsg(){return msg;}

    public void setMsg(String msg){this.msg = msg;}

    public MessageType getType(){return type;};

    public void setType(MessageType type){this.type = type;}

    public ArrayList<User> getUserlist(){return list;}

    public void setUserList(HashMap<String, User> userList){this.list = new ArrayList<>(userList.values());}

    public void setOnlineCount(int count){this.count = count;}

    public int getOnlineCount(){return this.count;}

    public void setPicture(String picture){this.picture = picture;}

    public ArrayList<User> getUsers(){return users;}

    public void setUsers(ArrayList<User> users){this.users = users;}

    public void setStatus(Status status){this.status = status;}

    public Status getStatus(){return status;}

    public void setVoiceMsg(byte[] voiceMsg){this.voiceMsg = voiceMsg;}


}

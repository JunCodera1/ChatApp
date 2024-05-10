package com.example.javafxchat.model.server;

import com.example.javafxchat.model.exception.DuplicateUsernameException;
import com.example.javafxchat.model.message.Message;
import com.example.javafxchat.model.message.MessageType;
import com.example.javafxchat.model.message.Status;
import com.example.javafxchat.model.message.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Server {
    /* Setting up variables */
    private static final int PORT = 5000;
    private static final HashMap<String, User> names = new HashMap<>();
    private static HashSet<ObjectOutputStream> writers = new HashSet<>();
    private static ArrayList<User> users = new ArrayList<>();
    static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try{
            while (true){
                new Handler(listener.accept()).start;
            }
        }
    }

    private static class Handler extends Thread{
        private String name;
        private Socket socket;
        private Logger logger = LoggerFactory.getLogger(Handler.class);
        private User user;
        private ObjectInputStream input;
        private OutputStream os;
        private ObjectOutputStream output;
        private InputStream is;
        public Handler(Socket socket) throws IOException{
            this.socket = socket;
        }
        public void run(){
            logger.info("Attempting to connect a user...");
            try{
                is = socket.getInputStream();
                input = new ObjectInputStream(is);
                os = socket.getOutputStream();
                output = new ObjectOutputStream(os);

                Message firstMessage = (Message) input.readObject();

            }
        }
        private Message changeStatus(Message inputmsg) throws IOException{
            logger.debug(inputmsg.getName() + " has changed status to " + inputmsg.getStatus());
            Message msg = new Message();
            msg.setName(user.getName());
            msg.setType(MessageType.STATUS);
            msg.setMsg("");
            User userObj = names.get(name);
            userObj.setStatus(inputmsg.getStatus());
            write(msg);
            return msg;
        }

        private synchronized void checkDuplicateUsername(Message firstMessage) throws DuplicateUsernameException{
            logger.info(firstMessage.getName() + " is trying to connect");
            if(!names.containsKey(firstMessage.getName())){
                this.name = firstMessage.getName();
                user = new User();
                user.setName(firstMessage.getName());
                user.setStatus(Status.ONLINE);
                user.setPicture(firstMessage.getPicture());

                users.add(user);
                names.put(name, user);

                logger.info(name + " has been added to the list");
            }else{
                logger.error(firstMessage.getName() + " is already connected");
                throw new DuplicateUsernameException(firstMessage.getName() + " is already connected");
            }
        }
        private Message sendNotification(Message firstMessage) throws IOException{
            Message msg = new Message();
            msg.setMsg("has joined the chat.");
            msg.setType(MessageType.NOTIFICATION);
            msg.setName(firstMessage.getName());
            msg.setPicture(firstMessage.getPicture());
            write(msg);
            return msg;
        }

        private Message removeFromList() throws IOException{
            logger.debug("removeFromList() method Enter");
            Message msg = new Message();
            msg.setMsg(" has left the chat.");
            msg.setType(MessageType.DISCONNECTED);
            msg.setName("SERVER");
            msg.setUserList(names);
            write(msg);
            logger.debug("removeFromList() method Exit");
            return msg;
        }
    }


}

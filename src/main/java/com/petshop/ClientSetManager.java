package com.petshop;

import java.util.HashSet;

public class ClientSetManager {

    HashSet<String> clientsEmail = new HashSet<>();

    public boolean addEmail(String email){
       return clientsEmail.add(email);
    }

    public boolean removeEmail(String email){
       return clientsEmail.remove(email);
    }

    public boolean findEmail(String email){
        for (String emails : clientsEmail){
            if (email.equals(emails)){
                return true;
            }
        }
        return false;
    }

}

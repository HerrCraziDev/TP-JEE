package co.chen.service;

import java.util.Date;

import co.chen.bean.Client;
import co.chen.utils.ClientState;

public class ClientManager {
    
    public void createClient(Client client) {
        client.setCreationDate(new Date());
        client.setState(ClientState.CREATED);
        System.out.println("\33[32mNew client created: " + client + "\33[0m");
    }
}

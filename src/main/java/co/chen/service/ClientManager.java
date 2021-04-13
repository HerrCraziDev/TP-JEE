package co.chen.service;

import java.util.Date;
import java.util.List;

import co.chen.bean.Client;
import co.chen.dao.ClientDAO;
import co.chen.utils.ClientState;

public class ClientManager {
    
    public void createClient(Client client, ClientDAO dao) {
        client.setCreationDate(new Date());
        client.setState(ClientState.CREATED);

        dao.createClient(client);
    }

    public List<Client> fetchClients(ClientDAO dao) {
        return dao.fetchClients();
    }
}

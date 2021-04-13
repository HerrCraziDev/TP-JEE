package co.chen.dao;

import java.util.List;

import co.chen.bean.Client;

public interface ClientDAO {
    public void createClient(Client client);
    public List<Client> fetchClients();
}

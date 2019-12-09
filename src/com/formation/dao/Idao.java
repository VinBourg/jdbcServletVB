package com.formation.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.formation.model.Client;

public interface Idao {
	
	void save(Client client);
	
	Client findById(int id);
	
	void update(Client client);
	
	void delete(Client client);
	
	Connection connecter() throws ClassNotFoundException, SQLException;

}

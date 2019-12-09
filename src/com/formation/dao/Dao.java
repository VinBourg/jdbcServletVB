package com.formation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.formation.model.Client;

public class Dao implements Idao {

	@Override
	public void save(Client client) {
		PreparedStatement st = null;
		Connection cn = null;

		String nom = client.getNom();
		String prenom = client.getPrenom();
		int age = client.getAge();

		try {
			// Connection à la BDD
			cn = connecter();

			// Ecrire une requete
			String sql = "INSERT INTO client (nom,prenom,age) VALUES (?,?,?)";

			// Creation du Statement
			st = cn.prepareStatement(sql);
			st.setString(1, nom);
			st.setString(2, prenom);
			st.setInt(3, age);

			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// etape 5 : liberer ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Client findById(int Id) {
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Client client = new Client();

		try {

			// etape 2 : récuperation de la connexion
			cn = connecter();
			// etape 3 : création d'un statement
			// st = cn.createStatement();
			// ecrire une requete
			String sql = "SELECT * FROM client WHERE Id=(?)";
			st = cn.prepareStatement(sql);
			st.setInt(1, Id);
			// etape 4 : exécution requete:
			rs = st.executeQuery();

			// etape 5 (parcours ResultSet)
			while (rs.next()) {
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setAge(rs.getInt("age"));
//				System.out.println(rs.getString("nom") + " " + rs.getString("prenom"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				// etape 6 : liberer ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	@Override
	public void update(Client client) {

	}

	@Override
	public void delete(Client client) {

	}

	@Override
	public Connection connecter() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/vente";
		String login = "root";
		String pwd = "";

		// Etape 1 : Chargement du Driver
		Class.forName("com.mysql.jdbc.Driver");
		// Etape 2 : Récupération de la connection
		Connection cn = DriverManager.getConnection(url, login, pwd);
		return cn;
	}

	public static void main(String[] args) {

//		Client client = new Client("Bourgueil", "Vincent", 35);
//		System.out.println(client);
//		Client client2 = new Client();
//		client2.setNom("toto");
//
//		Idao dao = new Dao();
		Dao dao = new Dao();
//		dao.save(client);

		System.out.println(dao.findById(1));

	}

}

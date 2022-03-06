package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Telefone;
import model.Userposjava;

public class UserPosDao {

	private Connection connection;

	public UserPosDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava user) {
		try {
			String sql = "INSERT INTO userposjava (nome, email) values (?, ?)";

			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, user.getNome());
			insert.setString(2, user.getEmail());
			insert.execute();
			connection.commit();// salva no banco

		} catch (Exception e) {
			try {
				connection.rollback();// volta o banco ao estado anterior
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Userposjava> listar() throws Exception {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "SELECT * FROM userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userposjava userposjava = new Userposjava();

			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			list.add(userposjava);
		}

		return list;
	}

	public Userposjava buscar(Long id) throws Exception {
		Userposjava userposjava = new Userposjava();
		String sql = "SELECT * FROM userposjava WHERE id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

		}

		return userposjava;
	}

	public void editar(Userposjava user) {
		try {
			String sql = "UPDATE userposjava SET nome = ? WHERE id = " + user.getId();

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, user.getNome());

			statement.execute();

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void delete(Long id) {
		try {
			String sql = "DELETE FROM userposjava WHERE id = ? ";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, id);

			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {

			String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa) VALUES ( ?, ?, ?) ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getNrUser());
			statement.execute();

			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();// volta o banco ao estado anterior
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
			e.printStackTrace();
		}
	}
}

package teste;

import java.util.List;

import org.junit.Test;

import dao.UserPosDao;
import model.Telefone;
import model.Userposjava;

public class TesteJdbc {

	@Test
	public void initBanco() {
		UserPosDao userPosDao = new UserPosDao();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("jess3");
		userposjava.setEmail("jess@gmail.com");

		userPosDao.salvar(userposjava);

	}

	@Test
	public void initLista() throws Exception {
		UserPosDao dao = new UserPosDao();

		List<Userposjava> lista = dao.listar();

		lista.forEach(e -> System.out.print(e));
	}

	@Test
	public void initBuscar() throws Exception {
		UserPosDao dao = new UserPosDao();

		Userposjava busca = dao.buscar(1L);

		System.out.println(busca);
	}

	@Test
	public void initAtualizar() throws Exception {
		UserPosDao dao = new UserPosDao();
		Userposjava userposjava = new Userposjava();
		userposjava.setId(1L);
		Userposjava busca = dao.buscar(userposjava.getId());
		busca.setNome("Constantino Barreto2");
		dao.editar(busca);

		System.out.println(busca);
	}

	@Test
	public void initDeletar() throws Exception {
		UserPosDao dao = new UserPosDao();

		dao.delete(1L);

		System.out.println("Deletou");
	}

	@Test
	public void inserirTelefone() {
		Telefone tel = new Telefone();
		UserPosDao dao = new UserPosDao();

		tel.setNumero("(95)1544444");
		tel.setTipo("CASA");
		tel.setNrUser(5L);

		dao.salvarTelefone(tel);

	}

	@Test
	public void buscaUSuarioCompleto() {
		Userposjava user = new Userposjava();
		UserPosDao dao = new UserPosDao();

		try {
			user = dao.buscar(2L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dao.listaCompleta(user.getId());

	}

	@Test
	public void deletaEmCascata() {
		UserPosDao dao = new UserPosDao();

		try {
			dao.deleteFonePorUser(3L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

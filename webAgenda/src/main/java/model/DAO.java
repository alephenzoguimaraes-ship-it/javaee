package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/** The Driver. */
	private String Driver = "com.mysql.cj.jdbc.Driver";
	
	/** The Url. */
	private String Url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The Usuario. */
	private String Usuario = "root";
	
	/** The Senha. */
	private String Senha = "jnjc2025";
	
	/**
	 * Conectar.
	 *
	 * @return the connection
	 * @throws ClassNotFoundException the class not found exception
	 */
	private Connection conectar() throws ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName(Driver);
			con = DriverManager.getConnection(Url, Usuario, Senha);
			return con;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values (?, ?, ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos(){
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Selectionar contato.
	 *
	 * @param contato the contato
	 */
	public void selectionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?, fone=?, email=? where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

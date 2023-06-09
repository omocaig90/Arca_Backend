package integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integration.enumeratori.EnumSpecie;
import integration.pojo.AnimaleDto;

public class ArcaDao {

	public static boolean insert(AnimaleDto animaleDto) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {

			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("INSERT INTO arcaweb.arca (id, peso, specie) VALUES (?,?,?)");
			stmt.setInt(1, animaleDto.getId());
			stmt.setInt(2, animaleDto.getPeso());
			stmt.setString(3, animaleDto.getSpecie().name());

			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
	}

	public static boolean delete(Integer id) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {

			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("DELETE FROM arcaweb.arca WHERE id = ? ");
			stmt.setInt(1, id);

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
	}

	public static Map<Integer, AnimaleDto> select() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		Map<Integer, AnimaleDto> mapAnimali;
		mapAnimali = new HashMap<>();

		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT id, peso, specie FROM arca");
			while (rs.next()) {
				AnimaleDto animale = new AnimaleDto();
				animale.setId(rs.getInt("id"));
				animale.setPeso(rs.getInt("peso"));
				animale.setSpecie(EnumSpecie.valueOf(rs.getString("specie")));
				mapAnimali.put(animale.getId(), animale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
		return mapAnimali;
	}
	
	public static List<EnumSpecie> selectSpeci() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<EnumSpecie> speci;
		speci= new ArrayList<>();
		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT specie FROM arcaweb.config_specie ");
			while (rs.next()) {
				speci.add(EnumSpecie.valueOf(rs.getString("specie")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
		return speci;
	}
	
	public static AnimaleDto selectAnimale(Integer id) {
		String query ="SELECT * FROM arca WHERE id= ?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AnimaleDto animale = new AnimaleDto();

		try {

			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
			animale.setId(rs.getInt("id"));
			animale.setPeso(rs.getInt("peso"));
			animale.setSpecie(EnumSpecie.valueOf(rs.getString("specie")));
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
		return animale;
	}

	public static boolean update(Integer id, Integer peso) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {

			con = ConnectionManager.getConnection();
			stmt = con.prepareStatement("UPDATE arcaweb.arca SET peso=? WHERE id = ? ");
			stmt.setInt(1, peso);
			stmt.setInt(2, id);
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			}

		}
	}

	
}

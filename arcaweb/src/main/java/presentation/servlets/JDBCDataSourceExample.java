package presentation.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;

import integration.ConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/JDBCDataSourceExample")
public class JDBCDataSourceExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery("select id, peso, specie from arca");

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print("<html><body><h2>Dettaglio Animali</h2>");
			out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
			out.print("<th>id</th>");
			out.print("<th>peso</th>");
			out.print("<th>specie</th>");

			while (rs.next()) {
				out.print("<tr>");
				out.print("<td>" + rs.getInt("id") + "</td>");
				out.print("<td>" + rs.getInt("peso") + "</td>");
				out.print("<td>" + rs.getString("specie") + "</td>");
				out.print("</tr>");
			}
			out.print("</table></body><br/>");

			// lets print some DB information
			
			out.print("</html>");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				// ctx.close();
			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			} /*
				 * catch (NamingException e) {
				 * System.out.println("Exception in closing Context"); }
				 */

		}
	}

}

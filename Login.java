import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException {
        
    	res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carstore", "root", "Malli@6969");
            PreparedStatement ps = conn.prepareStatement("Select name,pass from cars where name=? and pass=?");
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            
            RequestDispatcher rd=req.getRequestDispatcher("index.html"); 
            rd.include(req, res);
            
            if (rs.next()) {
                out.println("<br><br>Correct login credentials");
                HttpSession s=req.getSession();
				s.setAttribute("name",name);
				RequestDispatcher rd1=req.getRequestDispatcher("home1.html"); 
	            rd1.forward(req, res);
            } 
            else {
            	out.println("<br><br>Incorrect login credentials");
            	out.println("<br><br>Plz login again or register");
            }
        } 
        catch (Exception e) {
            out.println(e);
        }
    }
}

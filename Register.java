import java.io.*;  
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
  
public class Register extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String n=request.getParameter("name");  
String p=request.getParameter("pass");  
String e=request.getParameter("email");  
String c=request.getParameter("country");  
          
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carstore","root","Malli@6969");  
  
PreparedStatement ps=con.prepareStatement("insert into cars values(?,?,?,?)");  
  
ps.setString(1,n);  
ps.setString(2,p);  
ps.setString(3,e);  
ps.setString(4,c);  
          
int i=ps.executeUpdate();  
if(i>0) {
out.print("<b>You are successfully registered...</b>");  
RequestDispatcher rd1=request.getRequestDispatcher("login.html"); 
rd1.forward(request, response);     
}
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
  
}
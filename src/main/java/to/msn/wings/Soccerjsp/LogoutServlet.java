package to.msn.wings.Soccerjsp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
     
        HttpSession session= request.getSession();
        
        //sessionがあれば
        if(session != null) {
        	session.invalidate();
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/Soccer_LogOut.jsp");
        rd.forward(request,response);
        

    }
}

package to.msn.wings.Soccerjsp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        
        String userId = request.getParameter("j_userId");
        String passwd = request.getParameter("j_password");
        
        RequestDispatcher rd;
        String send;

        try {
            request.login(userId, passwd);
            Usr usr = new Usr(userId,UserNameDAO.UserName(userId));
            
            HttpSession session = request.getSession();
            session.setAttribute("usr", usr);
            
            send ="SoccerMenu.jsp";
    		rd=request.getRequestDispatcher(send);
    		rd.forward(request, response);
            
        } catch (ServletException e) {
        	request.setAttribute("error","error");
        	
        	send ="/Soccer_Login.jsp";
        	rd=request.getRequestDispatcher(send);
        	rd.forward(request, response);
        }
    }
}

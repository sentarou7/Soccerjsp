package to.msn.wings.Soccerjsp;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Country_PairingServlet")
public class Country_PairingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        
        String actType = request.getParameter("actType");
        
        request.setAttribute("actType", actType);
        
        request.setAttribute("Country_list", new ListBoxDAO().Country_list());
        
        RequestDispatcher rd =request.getRequestDispatcher("/Soccer_form_Countries.jsp");
        rd.forward(request, response);
    }
}

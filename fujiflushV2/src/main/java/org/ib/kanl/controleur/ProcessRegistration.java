package org.ib.kanl.controleur;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/processRegistration")
public class ProcessRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ProcessRegistration() {
        super();

    }

    /*
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String pseudo=request.getParameter("pseudo");
        String password=request.getParameter("password");
        String confirmPass=request.getParameter("confirmPass");

        RequestDispatcher rd1=request.getRequestDispatcher("register.jsp");
        RequestDispatcher rd2=request.getRequestDispatcher("login.jsp");
        PrintWriter out=response.getWriter();

//        UserDao dao = new UserDao();
//        try {
//            boolean isExist=dao.isExist(pseudo);
//            if(isExist) {
//                out.println("joueur avec ce pseudo "+pseudo+" deja existe.");
//                rd1.include(request, response);
//            }else {
//                if(!password.equals(confirmPass)) {
//                    out.println("password et confirme password sont differents");
//                    rd1.include(request, response);
//                }else {
//                    dao.registerUser(email,pseudo,password);
//                    out.println("joueur bien enregistré");
//                    rd2.include(request, response);
//                }
//            }
//        } catch (Exception e) {
//
//            out.println("erreur");
//        }

    }

}










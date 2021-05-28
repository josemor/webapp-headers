package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>Login Correcto</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <h1>Login Correcto!</h1>");
                writer.println("        <h3>Hola " + username + " has iniciado sesión con éxito! </h3>");
                writer.println("    </body>");
                writer.println("</html>");
            }


        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
                    "Lo sentimos no esta autorizado para ingresar a esta pagina!");
        }
    }
}

package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/hora-actualizada")
public class HoraActualizadaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("refresh", "1");

        LocalTime hora = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>La hora actualizada</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>La hora actualizada!</h1>");
            writer.println("<h3>" + hora.format(timeFormatter) + "</h3>");
            writer.println("    </body>");
            writer.println("</html>");
        }

    }
}

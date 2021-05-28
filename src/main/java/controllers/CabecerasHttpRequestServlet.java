package controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod();
        String requesUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contexPath = req.getContextPath();
        String ipCliente = req.getRemoteAddr();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String schema = req.getScheme();
        String host = req.getHeader("host");
        String url = schema + "://" + host + contexPath + servletPath;
        String url2 = schema + "://" + ip + ":" + port + contexPath + servletPath;

        try (PrintWriter writer = resp.getWriter()) {

            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>Cabeceras HTTP Request</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>Cabeceras HTTP Request!</h1>");
            writer.println("<ul>");
            writer.println("<li>metodo http: " + metodoHttp + "</li>");
            writer.println("<li>request uri: " + requesUri + "</li>");
            writer.println("<li>request url: " + requestUrl + "</li>");
            writer.println("<li>context path: " + contexPath + "</li>");
            writer.println("<li>servlet path: " + servletPath + "</li>");
            writer.println("<li>ip cliente: " + ipCliente + "</li>");
            writer.println("<li>ip local: " + ip + "</li>");
            writer.println("<li>puerto: " + port + "</li>");
            writer.println("<li>schema: " + schema + "</li>");
            writer.println("<li>host: " + host + "</li>");
            writer.println("<li>url: " + url + "</li>");
            writer.println("<li>url Dos: " + url2 + "</li>");

            Enumeration<String> headersNames = req.getHeaderNames();
            while (headersNames.hasMoreElements()) {
                String cabecera = headersNames.nextElement();
                writer.println("<li>" + cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }

            writer.println("</ul>");
            writer.println("    </body>");
            writer.println("</html>");
        }

    }
}

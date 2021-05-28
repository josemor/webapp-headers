package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import service.ProductoService;
import serviceImpl.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto  = mapper.readValue(jsonStream, Producto.class);
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("    <head>");
            writer.println("        <meta charset=\"UTF-8\">");
            writer.println("        <title>Detalle de producto desde JSON</title>");
            writer.println("    </head>");
            writer.println("    <body>");
            writer.println("        <h1>Detalle de producto desde JSON!</h1>");
            writer.println("<ul>");
            writer.println("<li>Id: " + producto.getId() + "</li>");
            writer.println("<li>Nombre: " + producto.getNombre() + "</li>");
            writer.println("<li>Tipo: " + producto.getTipo() + "</li>");
            writer.println("<li>Tipo: " + producto.getPrecio() + "</li>");
            writer.println("</ul>");
            writer.println("    </body>");
            writer.println("</html>");
        }

    }
}

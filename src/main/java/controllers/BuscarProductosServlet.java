package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import service.ProductoService;
import serviceImpl.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductosServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService productoService = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");
        Optional<Producto> encontrado = productoService.listar().stream().filter(producto -> {

            if ( nombre == null || nombre.isEmpty()) {
                return false;
            }
            return producto.getNombre().contains(nombre);
        }).findFirst();
        if (encontrado.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("   <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>Producto encontrado</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <h1>Producto encontrado!</h1>");
                writer.println("        <h3>Producto encontrado: " + encontrado.get().getNombre() +
                        " el precio $"+ encontrado.get().getPrecio() + "</h3>");
                writer.println("    </body>");
                writer.println("</html>");
            }


        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo sentimos no se encontró el producto " + nombre);
        }

    }
}

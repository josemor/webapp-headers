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
import java.util.List;

@WebServlet(
        { "/productos.xls", "/productos.html", "/productos"})
public class ProductoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        resp.setContentType("text/html;charset=UTF-8");
        String serverPath = req.getServletPath();
        boolean isXls = serverPath.endsWith(".xls");

        if (isXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }

        try (PrintWriter writer = resp.getWriter()) {
            if (!isXls) {
                writer.println("<!DOCTYPE html>");
                writer.println("<html>");
                writer.println("    <head>");
                writer.println("        <meta charset=\"UTF-8\">");
                writer.println("        <title>Listado de productos</title>");
                writer.println("    </head>");
                writer.println("    <body>");
                writer.println("        <h1>Listado de productos!</h1>");
                writer.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a xls</a></p>");
                writer.println("<p><a href=\"" + req.getContextPath() + "/productos.json" + "\">Mostrar JSON</a></p>");
            }
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>id</th>");
            writer.println("<th>nombre</th>");
            writer.println("<th>tipo</th>");
            writer.println("<th>precio</th>");
            writer.println("</tr>");
            productos.forEach(producto -> {
                writer.println("<tr>");
                writer.println("<td>" + producto.getId() + "</td>");
                writer.println("<td>" + producto.getNombre() + "</td>");
                writer.println("<td>" + producto.getTipo() + "</td>");
                writer.println("<td>" + producto.getPrecio() + "</td>");
                writer.println("</tr>");
            });
            writer.println("</table>");
            if (!isXls) {
                writer.println("    </body>");
                writer.println("</html>");
            }
        }

    }
}

package serviceImpl;

import models.Producto;
import service.ProductoService;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "NoteBook", "Computación", 1750000),
                new Producto(2L, "Mesa escritorio", "Oficina", 100000),
                new Producto(3l, "Teclado mecánico", "Computación", 400000));
    }
}

package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.dto.ProductDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface IPdfService {

    public ByteArrayOutputStream generatePdfProductos (List<ProductDTO> productos) throws IOException;
}

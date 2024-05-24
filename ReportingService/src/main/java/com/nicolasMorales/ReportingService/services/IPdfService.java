package com.nicolasMorales.ReportingService.services;

import com.nicolasMorales.ReportingService.dtos.ProductDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public interface IPdfService {

    public ByteArrayOutputStream generatePdfIngresos (List <ProductDTO> productos) throws IOException;
}

package com.nicolasMorales.ReportingService.services;

import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface IPdfService {

    public ByteArrayOutputStream generatePdf () throws IOException;
}

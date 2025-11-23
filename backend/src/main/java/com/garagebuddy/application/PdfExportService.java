package com.garagebuddy.application;

import com.garagebuddy.domain.Car;
import org.springframework.stereotype.Service;

@Service
public class PdfExportService {
    public byte[] exportCarHistoryToPdf(Car car) {
        // Placeholder: return empty PDF bytes for now. Implement using OpenPDF.
        return new byte[0];
    }
}

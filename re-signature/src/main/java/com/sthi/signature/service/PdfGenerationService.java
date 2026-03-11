package com.sthi.signature.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.sthi.signature.entity.Agreement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
@Service
public class PdfGenerationService {

    public String generateDraftPdf(String content, String savePath) {
        try {
            File file = new File(savePath);
            file.getParentFile().mkdirs();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();
            Font font = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Paragraph p = new Paragraph(content, font);
            document.add(p);
            document.close();

            log.info("Draft PDF generated successfully at {}", savePath);
            return savePath;
        } catch (Exception e) {
            log.error("Error generating draft PDF", e);
            throw new RuntimeException("Draft PDF generation failed", e);
        }
    }

    public String generateFinalSignedPdf(Agreement agreement, String content, String savePath, String consentBlock) {
        try {
            File file = new File(savePath);
            file.getParentFile().mkdirs();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            // Add original agreement text
            Font textFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Paragraph textP = new Paragraph(content, textFont);
            document.add(textP);

            // Add a few blank lines
            document.add(new Paragraph("\n\n\n"));

            // Add Digital Consent Block
            Font consentFont = new Font(Font.COURIER, 10, Font.NORMAL);
            Paragraph consentP = new Paragraph(consentBlock, consentFont);
            document.add(consentP);

            document.close();

            log.info("Final Signed PDF generated successfully at {}", savePath);
            return savePath;
        } catch (Exception e) {
            log.error("Error generating final signed PDF", e);
            throw new RuntimeException("Final PDF generation failed", e);
        }
    }
}

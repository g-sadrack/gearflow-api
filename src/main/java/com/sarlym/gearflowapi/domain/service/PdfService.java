package com.sarlym.gearflowapi.domain.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.sarlym.gearflowapi.domain.model.OrdemServico;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;

    private PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] gerarPdf(OrdemServico ordem) { // Renomeie o parâmetro
        // Inicia um contexto Thymeleaf
        Context context = new Context();
        context.setVariable("ordem", ordem); // Corrija o nome da variável
        String html = templateEngine.process("pdf/ordem-servico", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.useFastMode();
            builder.withHtmlContent(html, "/");

            // Configura o PDF
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    // No PdfService:
    public String gerarHtml(OrdemServico ordem) {
        Context context = new Context();
        context.setVariable("ordem", ordem);
        return templateEngine.process("pdf/ordem-servico", context);
    }

}

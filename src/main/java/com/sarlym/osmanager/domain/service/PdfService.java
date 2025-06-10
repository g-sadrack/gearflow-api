package com.sarlym.osmanager.domain.service;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.sarlym.osmanager.domain.model.OrdemServico;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] gerarPdf(OrdemServico ordem) { // Renomeie o parâmetro
        // Inicia um contexto Thymeleaf
        Context context = new Context();
        context.setVariable("ordem", ordem); // Corrija o nome da variável

        String html = templateEngine.process("pdf/ordem-servico", context);

        // Geração do PDF
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
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

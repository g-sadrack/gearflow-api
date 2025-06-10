package com.sarlym.osmanager.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sarlym.osmanager.domain.model.OrdemServico;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public String gerarPdf(OrdemServico ordemServico) {
        Context context = new Context();
        context.setVariable("ordemServico", ordemServico);
        
        return templateEngine.process("pdf/ordem-servico", context);
    }


}

package com.sarlym.osmanager.api.dto.dtoconverter;

import com.sarlym.osmanager.api.dto.ClienteDTO;
import com.sarlym.osmanager.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClienteConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO paraDTO (Cliente cliente){
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> paraDTOLista (List<Cliente> clientes){
        return clientes.stream().map(this::paraDTO).toList();
    }
}

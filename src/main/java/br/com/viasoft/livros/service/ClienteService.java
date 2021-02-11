package br.com.viasoft.livros.service;

import br.com.viasoft.livros.framework.CrudService;
import br.com.viasoft.livros.framework.CrudServiceImpl;
import br.com.viasoft.livros.model.Cliente;

import java.util.List;

public interface ClienteService extends CrudService<Cliente, Long> {
    List<Cliente> findByNome(String nome);
}

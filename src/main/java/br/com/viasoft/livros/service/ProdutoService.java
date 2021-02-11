package br.com.viasoft.livros.service;

import br.com.viasoft.livros.framework.CrudService;
import br.com.viasoft.livros.model.Produto;

import java.util.List;

public interface ProdutoService extends CrudService<Produto, Long>  {
    List<Produto> findByAutor(String autor);
}

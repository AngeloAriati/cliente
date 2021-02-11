package br.com.viasoft.livros.service.impl;

import br.com.viasoft.livros.event.ClientePresave;
import br.com.viasoft.livros.framework.CrudServiceImpl;
import br.com.viasoft.livros.model.Cliente;
import br.com.viasoft.livros.model.Produto;
import br.com.viasoft.livros.repository.ClienteRepository;
import br.com.viasoft.livros.repository.ProdutoRepository;
import br.com.viasoft.livros.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.validation.Validator;
import java.util.Collections;
import java.util.List;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente, Long> implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private ApplicationEventPublisher appPublisher;

    @Override
    public JpaRepository<Cliente, Long> getRepository(){
        return clienteRepository;
    }

    @Override
    public List<Cliente> findAll(){
        List<Cliente> result = getRepository().findAll();
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Cliente> findByNome(String nome){
        return clienteRepository.findAllByNomeContainsIgnoreCase(nome);
    }


    @Override
    public void preSave(Cliente cliente) {
        appPublisher.publishEvent
                (new ClientePresave(this, cliente));
    }

    @Override
    public void postSave(Cliente cliente) {
        System.out.println(String.format("Passei pelo post save, o id é %d", cliente.getId()));
    }

    @Override
    @Transactional
    public T save(T t){
        preSave(t);
        t = getRepository().save(t);
        postSave(t);
        return t;
    }


}

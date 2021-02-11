package br.com.viasoft.livros.controle;

import br.com.viasoft.livros.dto.ClienteFormularioDTO;
import br.com.viasoft.livros.dto.ProdutoFormularioDTO;
import br.com.viasoft.livros.model.Cliente;
import br.com.viasoft.livros.model.Produto;
import br.com.viasoft.livros.repository.ClienteRepository;
import br.com.viasoft.livros.service.ClienteService;
import br.com.viasoft.livros.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/cliente")
    public String getInicio(Model model){
        List<Cliente> cliente = clienteService.findAll();
        model.addAttribute("cliente", cliente );
        return "cliente/listacliente";
    }
    @GetMapping("/cliente/novo")
    public String getCliente(ClienteFormularioDTO clienteFormularioDTO) {
        return "cliente/formulariocliente";
    }

    @GetMapping("/cliente/edit/{id}")
    public String editaCliente(@PathVariable("id") Long id, ClienteFormularioDTO clienteFormularioDTO, Model model) {
        Cliente cliente = clienteService.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        clienteFormularioDTO = new ClienteFormularioDTO(cliente);
        model.addAttribute("cliente", cliente);
        model.addAttribute("dto", clienteFormularioDTO);
        return "cliente/seupai";
    }

    @PostMapping("/cliente/salvar")
    public String formCliente(@Valid ClienteFormularioDTO clienteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/formulariocliente";
        }
        Cliente cliente = clienteDTO.toCliente();
        clienteService.save(cliente);
        return "cliente/formulariocliente";
    }

    @PostMapping("/cliente/salvar/{id}")
    public String saveClienteExistente  (@PathVariable("id") Long id, @Valid ClienteFormularioDTO clienteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "seupai";
        }
        Cliente cliente = clienteDTO.toCliente();
        cliente.setId(id);
        clienteService.save(cliente);
        return "redirect:/cliente/" + cliente.getId();
    }


    @GetMapping("/cliente/{id}")
    public String listaClienteById(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "cliente/clientedetail";
    }


    @GetMapping ("cliente/delete/{id}")
    public String removeCliente(@PathVariable("id") Long id, Principal principal){
        var roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> cargos = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        clienteService.delete(id);
        return "redirect:/cliente/";
    }
}

package br.com.viasoft.livros.dto;

import br.com.viasoft.livros.model.Cliente;
import br.com.viasoft.livros.model.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClienteFormularioDTO {
    private Long id;
    @NotEmpty(message = "O NOME É OBRIGATÓRIO SEU ANIMAL")
    private String nome;
   // @CPF(message= "NÃO É POSSIVEL Q VC N CONSEGUE COLOCAR UM CPF CERTO")
    private String cpf;
    private String email;
    private String endereco;

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setEmail(this.email);
        cliente.setEndereco(this.endereco);
        return cliente;
    }

    public ClienteFormularioDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.endereco = cliente.getEndereco();
    }





}

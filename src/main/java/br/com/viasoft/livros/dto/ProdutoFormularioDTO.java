package br.com.viasoft.livros.dto;

import br.com.viasoft.livros.model.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class ProdutoFormularioDTO {
    private long id;
    @NotEmpty(message= "Coloca um nome ae porra")
    private String nome;
    @NotEmpty
    private String autor;
    private String imagem;

        public Produto toProduto(){
            Produto produto = new Produto();
            produto.setId(this.id);
            produto.setNome(this.nome);
            produto.setAutor(this.autor);
            produto.setImagem(this.imagem);
            return produto;
        }

        public ProdutoFormularioDTO(Produto produto){
            this.id = produto.getId();
            this.nome = produto.getNome();
            this.autor = produto.getAutor();
            this.imagem = produto.getImagem();
        }

    }


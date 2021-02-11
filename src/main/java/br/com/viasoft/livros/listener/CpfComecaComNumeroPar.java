package br.com.viasoft.livros.listener;

import br.com.viasoft.livros.event.ClientePresave;
import org.springframework.context.ApplicationListener;

import javax.swing.text.html.Option;
import java.util.Optional;

public class CpfComecaComNumeroPar implements ApplicationListener<ClientePresave> {
    @Override
    public void onApplicationEvent(ClientePresave clientePresave) {
        var entidade = clientePresave.getCliente();
        Optional<Character> primeiroDigito = Optional
                .ofNullable(entidade.getCpf().charAt(0));

        if(primeiroDigito.isPresent()){
            String texto = Integer
                    .parseInt(String.valueOf(primeiroDigito.get()))
                    % 2 == 0 ? "é par" : "é im par";
            System.out.println(texto);
        }

    }
}

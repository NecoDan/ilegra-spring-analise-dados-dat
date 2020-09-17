package br.com.ilegra.spring.analyse.dat.model.dtos;

import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumoRelatorio {
    private Vendedor vendedor;
    private Cliente cliente;
    private Venda venda;
}

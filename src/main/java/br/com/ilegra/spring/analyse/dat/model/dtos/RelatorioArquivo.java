package br.com.ilegra.spring.analyse.dat.model.dtos;

import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;
import br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil;
import br.com.ilegra.spring.analyse.dat.utils.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import one.util.streamex.StreamEx;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioArquivo {

    private Long qtdeClientes;
    private Long qtdeVendedores;
    private Long idVendaCara;
    private String nomePiorVendedor;

    public RelatorioArquivo somaQtdeClientes(List<Cliente> clienteList) {
        somarQtdeClientes(clienteList);
        return this;
    }

    public RelatorioArquivo somaQtdeVendedores(List<Vendedor> vendedorList) {
        somarQtdeVendedores(vendedorList);
        return this;
    }

    public RelatorioArquivo identificaVendaMaisCaraProcessada(List<Venda> vendaList) {
        verificarVendaMaisCaraProcessada(vendaList);
        return this;
    }

    public RelatorioArquivo identificaPiorVendedorVendasProcessadas(List<Venda> vendaList) {
        verificarPiorVendedorVendasProcessadas(vendaList);
        return this;
    }

    public void somarQtdeClientes(List<Cliente> clienteList) {
        if (Objects.isNull(clienteList) || clienteList.isEmpty())
            return;

        this.qtdeClientes = StreamEx.of(clienteList)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Cliente::getCnpj))
                .distinct(Cliente::getCnpj)
                .map(Cliente::getCnpj)
                .count();
    }

    public void somarQtdeVendedores(List<Vendedor> vendedorList) {
        if (Objects.isNull(vendedorList) || vendedorList.isEmpty())
            return;

        this.qtdeVendedores = StreamEx.of(vendedorList)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Vendedor::getCpf))
                .distinct(Vendedor::getCpf)
                .map(Vendedor::getCpf)
                .count();
    }

    public void verificarVendaMaisCaraProcessada(List<Venda> vendaList) {
        if (Objects.isNull(vendaList) || vendaList.isEmpty())
            return;
        Optional<Venda> optionalVenda = getOptionalVendaFilterPricesMaiorVenda(vendaList);
        optionalVenda.ifPresent(venda -> this.idVendaCara = venda.getId());
    }

    private Optional<Venda> getOptionalVendaFilterPricesMaiorVenda(List<Venda> vendaList) {
        return vendaList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(AbstractEntity::getId))
                .max(Comparator.comparing(Venda::calculaValorTotalPrice));
    }

    public void verificarPiorVendedorVendasProcessadas(List<Venda> vendaList) {
        if (Objects.isNull(vendaList) || vendaList.isEmpty())
            return;
        Optional<Venda> optionalVenda = getOptionalVendaFilterPricesVendedorMenorVenda(vendaList);
        optionalVenda.ifPresent(venda -> this.nomePiorVendedor = venda.getVendedor().getName());
    }

    private Optional<Venda> getOptionalVendaFilterPricesVendedorMenorVenda(List<Venda> vendaList) {
        return vendaList.stream()
                .filter(Objects::nonNull)
                .filter(venda -> Objects.nonNull(venda.getVendedor()))
                .sorted(Comparator.comparing(AbstractEntity::getId))
                .min(Comparator.comparing(Venda::calculaValorTotalPrice));
    }

    @Override
    public String toString() {
        String valorZerado = "0";
        String separadorPadrao = ArquivoUtil.SEPARADOR_DEFAULT;

        return getToStringRelatorioQtdeClientes(valorZerado) + separadorPadrao
                + getToStringRelatorioQtdeVendedores(valorZerado) + separadorPadrao
                + getToStringRelatorioIdVendaMaisCara(valorZerado) + separadorPadrao
                + getToStringRelatorioNomePiorVendedor();
    }

    private String getToStringRelatorioQtdeClientes(String valorZerado) {
        return (Objects.nonNull(this.qtdeClientes) && this.qtdeClientes > 0) ? String.valueOf(this.qtdeClientes) : valorZerado;
    }

    private String getToStringRelatorioQtdeVendedores(String valorZerado) {
        return (Objects.nonNull(this.qtdeVendedores) && this.qtdeVendedores > 0) ? String.valueOf(this.qtdeVendedores) : valorZerado;
    }

    private String getToStringRelatorioIdVendaMaisCara(String valorZerado) {
        return (Objects.nonNull(this.idVendaCara) && this.idVendaCara > 0) ? String.valueOf(this.idVendaCara) : valorZerado;
    }

    private String getToStringRelatorioNomePiorVendedor() {
        return (Objects.isNull(this.nomePiorVendedor) || this.nomePiorVendedor.isEmpty()) ? "Vendedor Não Identificado" : this.nomePiorVendedor;
    }
}

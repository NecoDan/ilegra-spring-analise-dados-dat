package br.com.ilegra.spring.analyse.dat.model.vendas;

import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.utils.domain.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Venda extends AbstractEntity {

    @Getter
    @Setter
    private Vendedor vendedor;
    @Getter
    private List<ItemVenda> itemVendas;
    @Getter
    private BigDecimal valorTotal;

    public void adicionarItemVenda(ItemVenda itemVenda) {
        if (Objects.isNull(itemVenda))
            return;

        if (Objects.isNull(itemVendas))
            this.itemVendas = new ArrayList<>();

        itemVenda.setVenda(this);
        this.itemVendas.add(itemVenda);
    }

    public Venda addAllItensVenda(List<ItemVenda> itemVendaList) {
        itemVendaList.stream().filter(Objects::nonNull).forEach(this::adicionarItemVenda);
        return this;
    }

    public BigDecimal calculaValorTotalPrice() {
        if (isNotContainsItens())
            return BigDecimal.ZERO;
        this.valorTotal = calcularValorTotalPriceItens();
        return this.valorTotal;
    }

    public BigDecimal calcularValorTotalPriceItens() {
        return (isNotContainsItens()) ? BigDecimal.ZERO : getValorTotalPriceItensCalculado();
    }

    private BigDecimal getValorTotalPriceItensCalculado() {
        return BigDecimal.valueOf(this.itemVendas
                .stream()
                .filter(Objects::nonNull)
                .filter(ItemVenda::isPriceItemVendaInFilterValido)
                .mapToDouble(ItemVenda::getPriceItemVendaNumeric)
                .sum())
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean isNotContainsItens() {
        return (Objects.isNull(this.itemVendas) || this.itemVendas.isEmpty());
    }
}

package br.com.ilegra.spring.analyse.dat.model.vendas;


import br.com.ilegra.spring.analyse.dat.utils.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ItemVenda extends AbstractEntity {

    private Venda venda;
    private BigDecimal quantity;
    private BigDecimal price;

    public boolean isPriceItemVendaInFilterValido() {
        return Objects.nonNull(this.price);
    }

    public double getPriceItemVendaNumeric() {
        return (isPriceItemVendaInFilterValido()) ? this.price.doubleValue() : BigDecimal.ZERO.doubleValue();
    }
}

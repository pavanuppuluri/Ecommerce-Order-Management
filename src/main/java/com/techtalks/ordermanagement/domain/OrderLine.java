package com.techtalks.ordermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PURCHASE_ORDER_LINES")
@Entity
@Builder
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_id_generator")
    @SequenceGenerator(name="order_line_id_generator", sequenceName = "order_line_id_seq")
    @Column(name = "order_line_id")
    private Integer orderLineId;

    @Column(name = "item")
    private String item;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;


}

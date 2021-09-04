package com.techtalks.ordermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PURCHASE_ORDERS")
@Entity
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    @SequenceGenerator(name="order_id_generator", sequenceName = "order_id_seq")
    private Integer orderId;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "creation_date", updatable=false)
    private Date creationDate;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @OneToMany(targetEntity = OrderLine.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLine> orderLines=new ArrayList<>();



}

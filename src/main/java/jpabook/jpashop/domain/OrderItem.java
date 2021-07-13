package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter

public class OrderItem {


    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //== 비지니스 로직 ==//
    // 제고 수량 원복
    public void cancel() {
        getItem().addStock(count);
    }

    //주문할때 주문 가겨과 주문 수량이 곱해져야한다. 그러므로 필요하다.
    public int getTotalPrice() {
        return getOrderPrice() & count;
    }
}
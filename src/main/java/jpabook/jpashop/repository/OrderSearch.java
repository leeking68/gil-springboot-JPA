package jpabook.jpashop.repository;


import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderSearch {
    //파라미터 조건이 있으면 where문으로 검색
    private String memberName; //회원 이름
    private OrderStatus orderStatus; //주문 상태[ORDER, CANCEL]

}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

//    @Transactional // 트랜잭션 어노테이션을 통해 커밋이 일어난다. => 변경감지 발생 => DB에 업데이트콜
//    public void updateItem(Long itemId, Book param) {
//        Item findItem = itemRepository.findOne(itemId); // 영속성
//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        findItem.setStockQuantity(param.getStockQuantity());
//    }
@Transactional // 트랜잭션 어노테이션을 통해 커밋이 일어난다. => 변경감지 발생 => DB에 업데이트콜
public void updateItem(Long itemId, String name, int price, int stockQuantity) {
    Item findItem = itemRepository.findOne(itemId); // 영속성
//    findItem.setPrice(param.getPrice());
//    findItem.setName(param.getName());
//    findItem.setStockQuantity(param.getStockQuantity());
    findItem.setPrice(price);
    findItem.setName(name);
    findItem.setStockQuantity(stockQuantity);
}
//많으면DTO


//    @Transactional
//    void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
//        Item findItem = em.find(Item.class, itemParam.getId()); //같은 엔티티를 조회한다.
//                findItem.setPrice(itemParam.getPrice()); //데이터를 수정한다.
//    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}

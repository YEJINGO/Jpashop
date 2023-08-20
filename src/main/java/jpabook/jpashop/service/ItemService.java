package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    /**
     * 저장 로직
     */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 상품 전체 조회
     */
    public List<Item> findAllItem() {
        return itemRepository.findAll();
    }

    /**
     * 상품 하나 조회
     */
    public Item findOneItem(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}

package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.BasketEntity;
import com.gnom.spring2023.app.entity.BasketProductEntity;
import com.gnom.spring2023.app.entity.CompletedOrderEntity;
import com.gnom.spring2023.app.entity.CompletedOrderProductEntity;
import com.gnom.spring2023.app.exception.basket.BasketNotFoundException;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductAlreadyExistException;
import com.gnom.spring2023.app.exception.completedOrder.CompletedOrderNotFoundException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.repository.CompletedOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedOrderService {
    @Autowired
    private CompletedOrderRepo completedOrderRepo;

    @Autowired
    private CompletedOrderProductService completedOrderProductService;

    @Autowired
    private BasketProductService basketProductService;

    @Autowired
    private BasketService basketService;

    /**
     * Создание завершенного заказа
     * @param completedOrder Новый завершенный заказ
     * @return Созданный завершенный заказ
     */
    public CompletedOrderEntity create(CompletedOrderEntity completedOrder) {
        return completedOrderRepo.save(completedOrder);
    }

    /**
     * Получение всех завершенных заказов, принадлежащих пользователю
     * @param userId Id пользователя, список завершенных заказов которого нужно получить
     * @return Список завершенных заказов пользователя
     */
    public Iterable<CompletedOrderEntity> getAllByUserId(Long userId) {
        return completedOrderRepo.findAllByUserEntity_Id(userId);
    }

    /**
     * Получение завершенного заказа по его Id
     * @param id Id завершенного заказа, который нужно получить
     * @return Завершенный заказ
     * @throws CompletedOrderNotFoundException Завершенный заказ не найден
     */
    public CompletedOrderEntity getById(Long id) throws CompletedOrderNotFoundException {
        CompletedOrderEntity completedOrder = completedOrderRepo.findById(id).orElse(null);
        if (completedOrder == null) {
            throw new CompletedOrderNotFoundException();
        }
        return completedOrder;
    }

    /**
     * Повторение заказа. Копирует все товары из завершенного заказа в корзину
     * @param completedOrderId Id завершенного заказа, который нужно повторить
     * @param basketId Id корзины, куда нужно скопировать все товары из завершенного заказа
     * @throws BasketNotFoundException Корзина не найдена
     * @throws BasketProductAlreadyExistException Соотношение Корзины-Товара уже создано
     */
    public void repeatOrderByCompletedOrderIdAndBasketId(Long completedOrderId, Long basketId)
            throws BasketNotFoundException, BasketProductAlreadyExistException, ProductNotFoundException {
        Iterable<CompletedOrderProductEntity> completedOrderProducts =
                completedOrderProductService.getAllByCompletedOrderId(completedOrderId);
        for (CompletedOrderProductEntity completedOrderProduct : completedOrderProducts) {
            BasketEntity basket = basketService.getById(basketId);
            BasketProductEntity basketProduct = new BasketProductEntity();
            basketProduct.setCount(completedOrderProduct.getCount());
            basketProduct.setBasketEntity(basket);
            basketProduct.setProductEntity(completedOrderProduct.getProductEntity());
            basketProductService.create(basketProduct);
        }
    }
}

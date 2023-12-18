package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.BasketProductEntity;
import com.gnom.spring2023.app.exception.basket.BasketNotFoundException;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductAlreadyExistException;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductNotFoundException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.repository.BasketProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductService {
    @Autowired
    private BasketProductRepo basketProductRepo;

    @Autowired
    private BasketService basketService;

    /**
     * Создание соотношения Корзина-Товар
     * @param basketProduct Новое соотношение Корзина-товар
     * @return Созданное соотношение
     * @throws BasketProductAlreadyExistException Соотношение уже существует
     */
    public BasketProductEntity create(BasketProductEntity basketProduct) throws BasketProductAlreadyExistException,
            BasketNotFoundException, ProductNotFoundException {
        if (basketProductRepo.findByBasketEntity_IdAndProductEntity_Id(
                basketProduct.getBasketEntity().getId(), basketProduct.getProductEntity().getId()) != null
        ) {
            throw new BasketProductAlreadyExistException();
        }
        BasketProductEntity basketProductEntity = basketProductRepo.save(basketProduct);
        basketService.updateSum(basketProductEntity.getBasketEntity().getId());
        return basketProductEntity;
    }

    /**
     * Получение всех соотношений Корзина-Товар, принадлежащих заданной корзине
     * @param basketId ID корзины, соотношения которой нужно получить
     * @return Все соотношения, принадлежащие данной корзине
     */
    public Iterable<BasketProductEntity> getAllByBasketId(Long basketId) {
        return basketProductRepo.findAllByBasketEntity_Id(basketId);
    }

    /**
     * Удаление всех соотношений Корзина-Товар, принадлежащих заданной корзине
     * @param basketId Id корзины, соотношения которой нужно удалить
     */
    public void deleteAllByBasketId(Long basketId) throws BasketNotFoundException, ProductNotFoundException {
        basketProductRepo.deleteAllByBasketEntity_Id(basketId);
        basketService.updateSum(basketId);
    }

    /**
     * Удаление одного соотношения Корзина-Товар, принадлежащего заданной корзине и заданному товару
     * @param basketId Id корзины, соотношение которой нужно удалить
     * @param productId Id товара, соотношение которого нужно удалить
     */
    public void deleteOneByBasketIdAndProductId(Long basketId, Long productId) throws BasketNotFoundException,
            ProductNotFoundException {
        basketProductRepo.deleteByBasketEntity_IdAndProductEntity_Id(basketId, productId);
        basketService.updateSum(basketId);
    }

    /**
     * Изменить количество товара в корзине
     * @param id Id соотношения Корзина-Товар, количество которого нужно удалить
     * @param count Новое количество товара
     * @throws BasketProductNotFoundException Заданное соотношение не найдено
     */
    public void changeCountById(Long id, int count) throws BasketProductNotFoundException,
            BasketNotFoundException, ProductNotFoundException {
        BasketProductEntity basketProduct = basketProductRepo.findById(id).orElse(null);
        if (basketProduct == null) {
            throw new BasketProductNotFoundException();
        }
        basketProduct.setCount(count);
        basketProductRepo.save(basketProduct);
        basketService.updateSum(basketProduct.getBasketEntity().getId());
    }
}

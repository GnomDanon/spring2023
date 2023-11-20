package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.BasketProductEntity;
import com.gnom.spring2023.app.exception.basketProduct.BasketProductAlreadyExistException;
import com.gnom.spring2023.app.repository.BasketProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductService {
    @Autowired
    private BasketProductRepo basketProductRepo;

    /**
     * Создание соотношения Корзина-Товар
     * @param basketProduct Новое соотношение Корзина-товар
     * @return Созданное соотношение
     * @throws BasketProductAlreadyExistException Соотношение уже существует
     */
    public BasketProductEntity create(BasketProductEntity basketProduct) throws BasketProductAlreadyExistException {
        if (basketProductRepo.findByBasketEntity_IdAndProductEntity_Id(
                basketProduct.getBasketEntity().getId(), basketProduct.getProductEntity().getId()) != null
        ) {
            throw new BasketProductAlreadyExistException();
        }
        return basketProductRepo.save(basketProduct);
    }

    /**
     * Получение всех соотношений Корзина-Товар, принадлежащих заданной корзине
     * @param basketId ID корзины, соотношения которой нужно получить
     * @return Все соотношения, принадлежащие данной корзине
     */
    public Iterable<BasketProductEntity> getAllByBasketId(Long basketId) {
        return basketProductRepo.findAllByBasketEntity_Id(basketId);
    }
}

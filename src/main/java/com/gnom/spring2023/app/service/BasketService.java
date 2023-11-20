package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.BasketEntity;
import com.gnom.spring2023.app.entity.BasketProductEntity;
import com.gnom.spring2023.app.entity.ProductEntity;
import com.gnom.spring2023.app.exception.basket.BasketAlreadyExistException;
import com.gnom.spring2023.app.exception.basket.BasketNotFoundException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.repository.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    @Autowired
    private BasketRepo basketRepo;

    @Autowired
    private BasketProductService basketProductService;

    @Autowired
    private ProductService productService;

    /**
     * Создает новую корзину в таблице корзин
     * Вызывается во время создания нового пользователя
     * @param basket Новая корзина для нового пользователя
     * @return Созданная корзина
     * @throws BasketAlreadyExistException Корзина для этого пользователя уже существует
     */
    public BasketEntity create(BasketEntity basket) throws BasketAlreadyExistException {
        if (basketRepo.findByUserEntity_Id(basket.getUserEntity().getId()) != null) {
            throw new BasketAlreadyExistException();
        }
        return basketRepo.save(basket);
    }

    /**
     * Получение корзины по ID пользователя
     * @param userId ID пользователя, корзину которого нужно получить
     * @return Корзина
     * @throws BasketNotFoundException Корзина не найдена
     */
    public BasketEntity getByUserId(Long userId) throws BasketNotFoundException {
        BasketEntity basket = basketRepo.findByUserEntity_Id(userId);
        if (basket == null) {
            throw new BasketNotFoundException();
        }
        return basket;
    }

    /**
     * Получение корзины по ID
     * @param id ID корзины
     * @return Корзина
     * @throws BasketNotFoundException Корзина не найдена
     */
    public BasketEntity getById(Long id) throws BasketNotFoundException {
        BasketEntity basket = basketRepo.findById(id).orElse(null);
        if (basket == null) {
            throw new BasketNotFoundException();
        }
        return basket;
    }

    /**
     * Обновление суммарной стоимости корзины
     * Вызывается каждый раз, когда создается или изменяется соотношение Корзина-Товар
     * @param id ID корзины, суммарную стоимость которой нужно поменять
     * @throws ProductNotFoundException Товар, принадлежащий корзине, не был найден
     * @throws BasketNotFoundException Корзина не найдена
     */
    public void updateSum(Long id) throws ProductNotFoundException, BasketNotFoundException {
        Iterable<BasketProductEntity> basketProductEntities = basketProductService.getAllByBasketId(id);
        double newSum = 0;
        for (BasketProductEntity basketProduct : basketProductEntities) {
            ProductEntity product = productService.getById(basketProduct.getProductEntity().getId());
            newSum += basketProduct.getCount() * product.getCostPerUnit();
        }
        BasketEntity basket = getById(id);
        basket.setFinalCost(newSum);
        basketRepo.save(basket);
    }
}

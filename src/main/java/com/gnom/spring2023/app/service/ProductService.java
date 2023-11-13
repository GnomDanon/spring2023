package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.ProductEntity;
import com.gnom.spring2023.app.exception.product.ProductAlreadyExistException;
import com.gnom.spring2023.app.exception.product.ProductNotFoundException;
import com.gnom.spring2023.app.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    /**
     * Создает новый товар в таблице товаров
     * @param product Новый товар
     * @return Созданный товар
     * @throws ProductAlreadyExistException Товар с таким названием уже существует
     */
    public ProductEntity create(ProductEntity product) throws ProductAlreadyExistException {
        if (productRepo.findByName(product.getName()) != null) {
            throw new ProductAlreadyExistException();
        }
        return productRepo.save(product);
    }

    /**
     * Возвращает список всех имеющихся товаров
     * @return Список имеющихся товаров
     */
    public Iterable<ProductEntity> getAll() {
        return productRepo.findAll();
    }

    /**
     * Получение товара по его ID
     * @param id ID товара, который нужно получить
     * @return Найденный товар
     * @throws ProductNotFoundException Товар с таким ID не существует
     */
    public ProductEntity getById(Long id) throws ProductNotFoundException {
        ProductEntity product = productRepo.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    /**
     * Получение товара по его названию
     * @param name Название товара, который нужно получить
     * @return Найденный товар
     * @throws ProductNotFoundException Товар с таким названием не существует
     */
    public ProductEntity getByName(String name) throws ProductNotFoundException {
        ProductEntity product = productRepo.findByName(name);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

    /**
     * Получение имеющихся или не имеющихся на данный момент товаров
     * @param available Наличие / отсутствие получаемых товаров
     * @return Список найденных товаров
     */
    public Iterable<ProductEntity> getByAvailable(boolean available) {
        return productRepo.findByAvailable(available);
    }

    /**
     * Получение товаров заданного типа
     * @param type Тип получаемых товаров
     * @return Список найденных товаров
     */
    public Iterable<ProductEntity> getByType(String type) {
        return productRepo.findByProductType(type);
    }

    /**
     * Удаление товара по его названию
     * @param name Название товара, который необходимо удалить
     * @throws ProductNotFoundException Товар с таким именем не существует
     */
    public void deleteByName(String name) throws ProductNotFoundException {
        ProductEntity product = productRepo.findByName(name);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        productRepo.deleteByName(name);
    }

    /**
     * Обновление информации о товаре
     * @param id ID товара, который необходимо удалить
     * @param name Новое название товара
     * @param available Обновленное наличие товара
     * @param costPerUnit Обновленная стоимость за одну единицу товара
     * @param composition Обновленный состав товара
     * @throws ProductNotFoundException Товар с таким ID не существует
     */
    public void update(Long id, String name, boolean available, int costPerUnit, String composition)
            throws ProductNotFoundException{
        ProductEntity product = getById(id);
        product.setName(name);
        product.setAvailable(available);
        product.setCostPerUnit(costPerUnit);
        product.setComposition(composition);
        productRepo.save(product);
    }
}

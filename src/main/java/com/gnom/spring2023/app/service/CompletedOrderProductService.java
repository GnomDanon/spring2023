package com.gnom.spring2023.app.service;

import com.gnom.spring2023.app.entity.CompletedOrderProductEntity;
import com.gnom.spring2023.app.exception.completedOrderProduct.CompletedOrderProductAlreadyExistException;
import com.gnom.spring2023.app.repository.CompletedOrderProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedOrderProductService {
    @Autowired
    private CompletedOrderProductRepo completedOrderProductRepo;

    /**
     * Создание соотношение Завершенный заказ - Товар
     * @param completedOrderProduct Новое соотношение Завершенный заказ - Товар
     * @return Созданное соотношение
     * @throws CompletedOrderProductAlreadyExistException Соотношение уже существует
     */
    public CompletedOrderProductEntity create(CompletedOrderProductEntity completedOrderProduct)
            throws CompletedOrderProductAlreadyExistException {
        if (completedOrderProductRepo.findByCompletedOrderEntity_IdAndProductEntity_Id(
                completedOrderProduct.getCompletedOrderEntity().getId(),
                completedOrderProduct.getProductEntity().getId())
                != null) {
            throw new CompletedOrderProductAlreadyExistException();
        }
        return completedOrderProductRepo.save(completedOrderProduct);
    }

    /**
     * Получение всех соотношений Завершенный заказ - Товар, принадлежащих заданному завершенному заказу
     * @param completedOrderId Id завершенного заказа, соотношения которого нужно получить
     * @return Все соотношения, принадлежащие заданному завершенному заказу
     */
    public Iterable<CompletedOrderProductEntity> getAllByCompletedOrderId(Long completedOrderId) {
        return completedOrderProductRepo.findAllByCompletedOrderEntity_Id(completedOrderId);
    }
}

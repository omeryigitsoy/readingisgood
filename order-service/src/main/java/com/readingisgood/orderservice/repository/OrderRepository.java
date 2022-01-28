package com.readingisgood.orderservice.repository;

import com.readingisgood.orderservice.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
    List<Order> findAllByCustomerId(Long customerId, Pageable pageable);

    /*@Query("select o from order_table o where o.date between :startDate and :endDate")
    List<Order> findAllByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);*/

    List<Order> findAllByDateBetween(Date startDate,Date endDate);
}

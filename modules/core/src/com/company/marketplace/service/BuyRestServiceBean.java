package com.company.marketplace.service;

import com.company.marketplace.entity.*;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(BuyRestService.NAME)
public class BuyRestServiceBean implements BuyRestService {
    @Inject
    private DataManager dataManager;
    @Inject
    private Persistence persistence;
    @Inject
    private UniqueNumbersService uniqueNumbersService;
    @Inject
    private OnlineOrderService onlineOrderService;

    @Override
    public List<SoldProductDTO> getAvailableProduct() {
        List<SoldProductDTO> result;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<SoldProduct> list = em.createNativeQuery(
                            "select * from marketplace_sold_product as e", SoldProduct.class)
                    .getResultList();
            result = list.stream()
                    .map(soldProduct -> {
                        SoldProductDTO dto = new SoldProductDTO();
                        dto.setId(soldProduct.getId());
                        dto.setName(soldProduct.getProduct().getName());
                        dto.setPrice(soldProduct.getPrice());
                        return dto;
                    })
                    .collect(Collectors.toList());
            tx.commit();
        }
        return result;
    }

    @Override
    public Buyer createBuyer(Buyer buyer) {
        Buyer newBuyer;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            em.persist(buyer);
            newBuyer = em.find(Buyer.class, buyer.getId(), "_minimal");
            tx.commit();
        }
        return newBuyer;
    }

    @Override
    public OnlineOrder createOrder(Buyer buyer, List<BuyProductDTO> products, Integer discount) {
        OnlineOrder order;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            order = dataManager.create(OnlineOrder.class);
            order.setBuyer(buyer);
            order.setStatus(OrderStatus.PROCESSING);
            order.setProducts(products
                    .stream()
                    .map(pr -> {
                        SoldProduct sold = em.find(SoldProduct.class, pr.getId());
                        BuyProduct buy = dataManager.create(BuyProduct.class);
                        buy.setQuantity(pr.getQuantity());
                        buy.setPrice(sold.getPrice());
                        buy.setProduct(sold);
                        buy.setOnlineOrder(order);
                        return buy;
                    })
                    .collect(Collectors.toList()));
            order.setDiscount(discount);
            order.setStatus(OrderStatus.PROCESSING);
            order.setNumber(String.valueOf(uniqueNumbersService.getNextNumber("sequenceForOnlineOrder")));
            order.setAmount(onlineOrderService.calculateAmount(order));
            tx.commit();
        }
        return order;
    }

    @Override
    public String cancelOrder(UUID orderId) {
        String answer;
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            OnlineOrder order = em.find(OnlineOrder.class, orderId);
            if (order.getStatus() != OrderStatus.CANCELED) {
                order.setStatus(OrderStatus.CANCELED);
                answer = "is cancel now";
            } else {
                answer = "has already been canceled";
            }
            tx.commit();
        }
        return "OnlineOrder " + answer;
    }
}
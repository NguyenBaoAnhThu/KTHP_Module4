package org.example.finaltest.service;

import org.example.finaltest.model.Promotion;
import org.example.finaltest.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public void updatePromotion(Long id, Promotion promotion) {
        promotionRepository.save(promotion);
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
    public Promotion getPromotion(Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    public List<Promotion> searchPromotions(String discountAmount, String startDate, String endDate) {
        List<Promotion> promotions = promotionRepository.findAll();

        if (discountAmount != null && !discountAmount.isEmpty()) {
            promotions = promotions.stream()
                    .filter(p -> p.getDiscountAmount().toString().contains(discountAmount))
                    .collect(Collectors.toList());
        }
        if (startDate != null && !startDate.isEmpty()) {
            promotions = promotions.stream()
                    .filter(p -> p.getStartDate().compareTo(Date.valueOf(startDate)) >= 0)
                    .collect(Collectors.toList());
        }
        if (endDate != null && !endDate.isEmpty()) {
            promotions = promotions.stream()
                    .filter(p -> p.getEndDate().compareTo(Date.valueOf(endDate)) <= 0)
                    .collect(Collectors.toList());
        }
        return promotions;
    }
}

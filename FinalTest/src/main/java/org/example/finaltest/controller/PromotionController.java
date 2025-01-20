package org.example.finaltest.controller;

import org.example.finaltest.model.Promotion;
import org.example.finaltest.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public String listPromotions(Model model) {
        var promotions = promotionService.getAllPromotions();
        model.addAttribute("promotions", promotions);
        return "promotion/list";
    }

    @GetMapping("/add")
    public String addPromotionForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "promotion/add";
    }

    @PostMapping("/add")
    public String addPromotion(@ModelAttribute Promotion promotion) {
        promotionService.addPromotion(promotion);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPromotionForm(@PathVariable Long id, Model model) {
        Promotion promotion = promotionService.getPromotion(id);
        model.addAttribute("promotion", promotion);
        return "promotion/edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePromotion(@PathVariable Long id, @ModelAttribute Promotion updatedPromotion) {
        promotionService.updatePromotion(id, updatedPromotion);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String searchPromotions(@RequestParam(required = false) String discountAmount,
                                   @RequestParam(required = false) String startDate,
                                   @RequestParam(required = false) String endDate,
                                   Model model) {
        List<Promotion> promotions = promotionService.searchPromotions(discountAmount, startDate, endDate);
        model.addAttribute("promotions", promotions);
        return "promotion/list";
    }
}

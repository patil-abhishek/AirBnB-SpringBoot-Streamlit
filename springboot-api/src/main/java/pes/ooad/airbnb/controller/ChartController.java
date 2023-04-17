package pes.ooad.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {

    @Autowired
    private CustomerController customerController;

    @GetMapping("/")
    public String showChart(Model model) {
        List<CustomerAgeData> customerAgeData = customerController.getCustomerAgeData();
        model.addAttribute("customerAgeData", customerAgeData);
        return "chart";
    }
}

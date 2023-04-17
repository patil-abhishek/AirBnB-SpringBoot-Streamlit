package pes.ooad.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private ChartJs chartJs;

    @GetMapping("/customer-age-data")
    public List<CustomerAgeData> getCustomerAgeData() {
        // TODO: retrieve customer data from database or service
        // and calculate the number of customers in each age bracket
        List<CustomerAgeData> data = new ArrayList<>();
        data.add(new CustomerAgeData("Under 18", 100));
        data.add(new CustomerAgeData("18-24", 200));
        data.add(new CustomerAgeData("25-34", 300));
        data.add(new CustomerAgeData("35-44", 250));
        data.add(new CustomerAgeData("45-54", 150));
        data.add(new CustomerAgeData("Over 54", 100));

        chartJs.createPieChart("customerAgeChart", "Customer Age Data", data);

        return data;
    }
}

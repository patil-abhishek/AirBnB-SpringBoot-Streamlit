@RestController
public class CustomerController {

    @GetMapping("/customer-age-data")
    public List<CustomerAgeData> getCustomerAgeData() {
        // TODO: retrieve customer data from database or service and calculate the number of customers in each age bracket
        List<CustomerAgeData> data = new ArrayList<>();
        data.add(new CustomerAgeData("Under 18", 100));
        data.add(new CustomerAgeData("18-24", 200));
        data.add(new CustomerAgeData("25-34", 300));
        data.add(new CustomerAgeData("35-44", 250));
        data.add(new CustomerAgeData("45-54", 150));
        data.add(new CustomerAgeData("Over 54", 100));
        return data;
    }
}

public class CustomerAgeData {
    private String ageBracket;
    private int count;

    public CustomerAgeData(String ageBracket, int count) {
        this.ageBracket = ageBracket;
        this.count = count;
    }

    public String getAgeBracket() {
        return ageBracket;
    }

    public int getCount() {
        return count;
    }
}

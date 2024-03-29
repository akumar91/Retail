package com.retail.pricing.changeLog;

import com.retail.pricing.model.Detail;
import com.retail.pricing.model.Price;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ChangeUnit(id="client-initializer", order = "1", author = "akumar")
public class DatabaseChangeLog {

        @Execution
        public void execution(MongoTemplate mongoTemplate) {

            List<Price> inserteRecords = generateRandomData();
            mongoTemplate.insertAll(inserteRecords);
            mongoTemplate.insertAll(generateRandomData());
        }

        public List<Price> generateRandomData() {
            List<Price> randomPriceData = new ArrayList<>();

            Map<String, Detail> categoryWithDetails = new HashMap<>();

            // Furniture
            categoryWithDetails.put("Furniture", new Detail(1000 + (6000 - 1000) * new Random().nextDouble(), "USD", "couch"));
            categoryWithDetails.put("Furniture", new Detail(1000 + (2000 - 1000) * new Random().nextDouble(), "USD", "Table"));
            categoryWithDetails.put("Furniture", new Detail(600 + (2000 - 600) * new Random().nextDouble(), "USD", "Mattress"));
            categoryWithDetails.put("Furniture", new Detail(200 + (1000 - 200) * new Random().nextDouble(), "USD", "Chairs"));

            // Produce
            categoryWithDetails.put("Produce", new Detail(3 + (6 - 3) * new Random().nextDouble(), "USD", "Eggs"));
            categoryWithDetails.put("Produce", new Detail(3 + (8 - 3) * new Random().nextDouble(), "USD", "Milk"));
            categoryWithDetails.put("Produce", new Detail(2 + (3 - 2) * new Random().nextDouble(), "USD", "Yogurt"));
            categoryWithDetails.put("Produce", new Detail(2 + (5 - 2) * new Random().nextDouble(), "USD", "Apple"));

            // Electronics
            categoryWithDetails.put("Electronics", new Detail(800 + (3000 - 800) * new Random().nextDouble(), "USD", "Speakers"));
            categoryWithDetails.put("Electronics", new Detail(100 + (500 - 100) * new Random().nextDouble(), "USD", "Printer"));
            categoryWithDetails.put("Electronics", new Detail(700 + (2000 - 700) * new Random().nextDouble(), "USD", "LG HD Monitor"));
            categoryWithDetails.put("Furniture", new Detail(new Random().nextDouble(), "USD", "couch"));
            categoryWithDetails.put("Furniture", new Detail(new Random().nextDouble(), "USD", "Table"));
            categoryWithDetails.put("Furniture", new Detail(new Random().nextDouble(), "USD", "Mattress"));
            categoryWithDetails.put("Furniture", new Detail(new Random().nextDouble(), "USD", "Chairs"));

            // Produce
            categoryWithDetails.put("Produce", new Detail(new Random().nextDouble(), "USD", "Eggs"));
            categoryWithDetails.put("Produce", new Detail(new Random().nextDouble(), "USD", "Milk"));
            categoryWithDetails.put("Produce", new Detail(new Random().nextDouble(), "USD", "Yogurt"));
            categoryWithDetails.put("Produce", new Detail(new Random().nextDouble(), "USD", "Apple"));

            // Electronics
            categoryWithDetails.put("Electronics", new Detail(new Random().nextDouble(), "USD", "Speakers"));
            categoryWithDetails.put("Electronics", new Detail(new Random().nextDouble(), "USD", "Printer"));
            categoryWithDetails.put("Electronics", new Detail(new Random().nextDouble(), "USD", "LG HD Monitor"));

            for (Map.Entry<String, Detail> categoryWithDetail : categoryWithDetails.entrySet()) {
                System.out.println("************ Random Value ************ \n" + categoryWithDetail.getKey() + " :: " + categoryWithDetail.getValue());

                Price price = new Price(new Random().nextInt(5000), categoryWithDetail.getKey() , categoryWithDetail.getValue());

                randomPriceData.add(price);

            }

            return randomPriceData;
        }

        @RollbackExecution
        public void rollbackExecution(MongoTemplate mongoTemplate) {

            mongoTemplate.dropCollection("price");

        }
}

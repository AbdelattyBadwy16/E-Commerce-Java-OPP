package services.impl;

import interfaces.Shippable;
import services.ShippingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingServiceImpl implements ShippingService {
    @Override
    public void ship(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }

        System.out.println("** Shipment notice **");

        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }

        for (String name : itemCounts.keySet()) {
            int quantity = itemCounts.get(name);
            System.out.println(quantity + "x " + name + " " + itemWeights.get(name) * quantity + "g");
        }

        System.out.println("Total package weight " + (totalWeight/1000) + "kg");
        System.out.println();
    }
}

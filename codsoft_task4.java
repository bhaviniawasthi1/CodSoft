// TASK 4- CURRENCY CONVERTER

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// Main class
public class codsoft_task4 {

    // Method to fetch exchange rate from API
    private static double getExchangeRate(String base, String target) {
        try {
            // Free API (no API key required for basic usage)
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + base;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            // Read API response
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Convert response to string
            String json = response.toString();

            // Extract target currency rate manually (simple parsing)
            String searchKey = "\"" + target + "\":";
            int index = json.indexOf(searchKey);

            if (index == -1) {
                return -1; // currency not found
            }

            int start = index + searchKey.length();
            int end = json.indexOf(",", start);

            if (end == -1) {
                end = json.indexOf("}", start);
            }

            String rateStr = json.substring(start, end).trim();
            return Double.parseDouble(rateStr);

        } catch (Exception e) {
            System.out.println("Error fetching exchange rate. Please check your internet connection.");
            return -1;
        }
    }

    // Method to get currency symbol (basic mapping)
    private static String getSymbol(String currency) {
        switch (currency) {
            case "USD": return "$";
            case "INR": return "₹";
            case "EUR": return "€";
            case "GBP": return "£";
            case "JPY": return "¥";
            default: return currency;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Currency Converter -----");

        // Input base currency
        System.out.print("Enter base currency (e.g., USD, INR, EUR): ");
        String baseCurrency = scanner.next().toUpperCase();

        // Input target currency
        System.out.print("Enter target currency: ");
        String targetCurrency = scanner.next().toUpperCase();

        // Input amount
        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        // Fetch exchange rate
        double rate = getExchangeRate(baseCurrency, targetCurrency);

        if (rate == -1) {
            System.out.println("Unable to perform conversion. Please try again.");
            return;
        }

        // Perform conversion
        double convertedAmount = amount * rate;

        // Display result
        System.out.println("\n----- Conversion Result -----");
        System.out.printf("%.2f %s = %.2f %s%s\n",
                amount,
                baseCurrency,
                convertedAmount,
                getSymbol(targetCurrency),
                " (" + targetCurrency + ")"
        );

        scanner.close();
    }
}
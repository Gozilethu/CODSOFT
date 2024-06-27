/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package error.currencyconverter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.JSONObject;
/**
 *
 * @author admin
 */
public class CurrencyConverter {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");
        System.out.println("------$$$$$$$$--------");

       
        System.out.print("Enter the base currency (e.g. USD, EUR, JPY): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency (e.g. USD, EUR, JPY): ");
        String targetCurrency = scanner.next().toUpperCase();

       
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        JSONObject currencyRates = getCurrencyRates(baseCurrency);

        double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency, currencyRates);

        System.out.printf("%.2f %s is equal to %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
    }

    public static JSONObject getCurrencyRates(String baseCurrency) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create("https://api.exchangerate-api.com/v4/latest/" + baseCurrency))
               .GET()
               .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    public static double convertCurrency(double amount, String baseCurrency, String targetCurrency, JSONObject currencyRates) {
        if (!currencyRates.getJSONObject("rates").has(targetCurrency)) {
            return Double.NaN;
        }
        double exchangeRate = currencyRates.getJSONObject("rates").getDouble(targetCurrency);
        return amount * exchangeRate;
    }
}

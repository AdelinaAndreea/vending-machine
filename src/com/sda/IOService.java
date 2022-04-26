package com.sda;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

//nu are sens sa punem stock ca si proprietate a clasei
public class IOService {

    public void displayMainMenu(Map<Product,Tray> stock) {  //ia info din stoc si afiseaza
        //afiseara meniu
        //ia input de la utiliz
        //proceseaza
        System.out.println("Welcome! Please select a product from the list: ");
        for (Map.Entry<Product, Tray> entry : stock.entrySet()) {
            Product product=entry.getKey();
            Tray tray=entry.getValue();
            if(tray.isEmply()){
                continue;  //tava este goala->continua
            }
            System.out.println(product.getName()+"\t"+product.getQuanity()+"\t"+product.getPrice()+"\t"+product.getCode());
        }
    }

    public String getUserInput(){
        System.out.print("Your input: ");
        Scanner scanner=new Scanner(System.in);
        String userInput=scanner.nextLine();
        return userInput;
    }

    public void displayChosenProductInformation(Product product){
        System.out.println("You have selected "+product.name()+" whitch costs "+product.getPrice());

    }
    public void displayInputErrorMessage(){
        System.out.println("Sorry, the input is not valid");
    }

    public void displayPaymentMenu(){
        System.out.println("Please introduce the coin code that you want to insert");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getCode()+" for "+coin.getName());
        }
    }

    public void displayMissingValue(float missingValue){
        System.out.println("Still to pay: "+missingValue);
    }


    public void displayCoins(CoinBox coinBox) {
        System.out.println("Coinbox: "+coinBox.getVault());
    }
    public void displayChange(List<Coin> coins){
        for(Coin coin : coins){
            System.out.println("Releasing change a coin of value "+coin.getValue());
        }

    }

    public void displayReturnCoinsMessage(List<Coin> userCoins) {
        for(Coin coin : userCoins){
            System.out.println("Giving back "+coin.getName());
        }
    }
}

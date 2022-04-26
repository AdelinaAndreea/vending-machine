package com.sda;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

//in constructor se face instantierea!!!
public class VendingMachine {
    //products (on trays)
    //trays
    //coins (coin box-each type 1 leu,5 lei...)
    //coin box

    private Map<Product,Tray> stock;
    private IOService ioService;
    private PaymentService paymentService;

    public VendingMachine() {

        this.stock = new LinkedHashMap<>(); //pastreaza ordinea
        for(Product product:Product.values()){  //iterez prin fiecare produs din enum
            stock.put(product,new Tray());
        }
        this.ioService=new IOService();
        this.paymentService= new PaymentService(ioService);
    }

    public void loadWithProducts(int numberOfProducts){
        for(Map.Entry<Product,Tray>entry: stock.entrySet()){ //iterez prin map!  de la 0 la 3,adaug pe tava cate un produs
            Product product=entry.getKey();  //luam cheia=Product
            Tray tray=entry.getValue(); //luam valoarea=Tray
            for(int index=0;index<numberOfProducts;index++){
                tray.addProduct(product);
            }
        }
    }

    public void displayStock(){
        System.out.println(stock);
    }
    public void start(){
        while(stockIsNotEmpty()){
            //afiseaza meniu
            ioService.displayMainMenu(stock);
            //ia input de la utilizator
            String userInput=ioService.getUserInput();  //cand iau=>pun in var ca sa-l pot folosi
            //proceseaza inputul(bucla repetitiva cu nr cunoscut de pasi=for; cu nr necunoscut de pasi=do-while/while)
            process(userInput);
        }
    }

    private void process(String productCode) {
       Optional <Product> optionalProduct=getProductByCode(productCode);  //cutie care poate contine produs
        if(optionalProduct.isEmpty()){
         ioService.displayInputErrorMessage();
         return; //iesim din metoda!!(nu doar din if)  nu avem return "ceva" pt ca metoda este void
        }
        Product product=optionalProduct.get(); //ia continutul Optionalului,am Product. "despachetez" Optional
        ioService.displayChosenProductInformation(product);
        boolean paymentWasSuccessful=paymentService.processPaymentFor(product);
        if(paymentWasSuccessful){
          releaseProduct(product);

        }
    }

    private void releaseProduct(Product product) {
        Tray tray=stock.get(product);
        tray.removeProduct(product);
    }

    //Ex: intoarce cutie->poate are, sau nu produsul in ea. Optional=cutia care poate contine un produs
    private Optional<Product> getProductByCode(String productCode) {  // metoda=contract=>neaparat tre sa intoarca Product!! nu returneaza null!!
        for (Product product : Product.values()) {
            if(product.getCode().equals(productCode)){
                return Optional.of(product);
            }
        }
        return Optional.empty(); //returneaza cutia goala!
    }

    //daca o tavita nu este goala=>stockul nu e gol
    private boolean stockIsNotEmpty() {
        for (Map.Entry<Product, Tray> entry : stock.entrySet()) { //pt fiecare intrare in stock
            Tray tray=entry.getValue(); //daca tavita e goala=>trecem la urmatoarea tavita!!tre verificate toate!!!
            if(!tray.isEmply()){
                return true;
            }
        }
        return false; //in afara for=>nu a intrat niciodata in if. daca intra in if->returneaza true.

    }

    public void loadWithCoins(int numberOfCoins) {
        paymentService.loadWithCoin(numberOfCoins);
    }
}

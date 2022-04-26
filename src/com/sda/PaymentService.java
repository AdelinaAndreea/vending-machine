package com.sda;
// ca sa apelez o met statica folosesc numele clasei.metoda, fara sa instantiez obiectul
//var statica-ca un lift. toata lumea il acceseaza (in cheama la et1,sau 2)dar stie unde e
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//obiectul poate fi referentiat de mai multe variabile!! vezi ioService
public class PaymentService {
    private CoinBox coinBox;
    private IOService ioService;

    public PaymentService(IOService ioService) {   //ioService este acelasi cu ioService din vendingMachine
        this.ioService = ioService;
        this.coinBox=new CoinBox();
    }

    public boolean processPaymentFor(Product product) {
        //luam coin code-ul de la utilizator
        //pe baza codului identificam moneda
        //luam valoarea monedei si o adaugam la sold(care este initial 0)=>
        //daca soldul coincide return true
        //Daca NU coincide-> soldul mai mare decat pretul produsului-dam rest
        //-> soldul e mai mic ->calculam cati bani tre sa mai introduca.
        // remainingValue=product price-sold
        //afisam iarasi meniul de coins
        //luam codul de moneda de la utilizator si identificam moneda aferenta codului respectiv
        //luam valoarea monedei si o adaugam la sold
        //daca soldul coincide cu pretul->return true
        //daca soldul > pretul ii dam rest
        //soldul<pretul...repet
        //bucla repetitiva-while
        float balance = 0.0F;
        List<Coin> userCoins=new ArrayList<>();
        while (balance < product.getPrice()) {
            ioService.displayPaymentMenu();
            String coinCode = ioService.getUserInput();
            Optional<Coin> optionalCoin = Coin.getCoinByCode(coinCode);
            if (optionalCoin.isEmpty()) {
                ioService.displayInputErrorMessage();
                continue; //reiau pasii daca nu e buna moneda=>verifica iar conditia din while. nu vreau sa ies din while!
                          //break ne scoate din while, return ne scoate din metoda
            }
            //la linia asta avem sigur un coin in cutie. daca cutia era goala relua while de la capat,nu mai continua. not clean code cu else
            Coin coin = optionalCoin.get();
            userCoins.add(coin);
            balance += coin.getValue();
            if (balance == product.getPrice()) {

                return true;
            }
            if (balance > product.getPrice()) {
               boolean wasChangedReleased= releaseChange(balance-product.getPrice());
               if(wasChangedReleased){
                   coinBox.add(userCoins);
               }else{
                   ioService.displayReturnCoinsMessage(userCoins);
               }
               return wasChangedReleased;
            }
            //la linia asta stiu sigura ca balance<pret=>intra in while
            float missingValue = product.getPrice() - balance;
            ioService.displayMissingValue(missingValue);
        }
        return true;
    }


    private boolean releaseChange(float changeValue) {
        //identificam cea mai mare bancnota cu propr. ca <= ca valoarea restului
        //scoatem moneda din coinBox
        //actualizam val restului care tre dat utilizatorului
        //repet
        List<Coin> candidateChangeCoins=new ArrayList<>();
        float candidateCoinVaule=changeValue;
        while(changeValue>0){//restul >0
            Coin coin=Coin.getCoinByLowerOrEqualValue(candidateCoinVaule);
            Optional<Coin> optionalCoin=coinBox.getCoinFromVault(coin); //cutie cu posibila moneda
            if(optionalCoin.isPresent()){
              candidateChangeCoins.add(optionalCoin.get());
               changeValue=changeValue-optionalCoin.get().getValue();
            }else{
                candidateCoinVaule=candidateCoinVaule-1;//daca nu am 50 bani pt rest? am sch boolean in metoda
                if(candidateCoinVaule<=0){
                    coinBox.add(candidateChangeCoins);
                    return false;
                }
            }
        }
        ioService.displayChange(candidateChangeCoins);
        return true;
    }

    public void loadWithCoin(int numberOfCoins) {
        for(Coin coin : Coin.values()){
            for(int index=0; index<numberOfCoins;index++){
                coinBox.add(coin);
            }
        }
    }
}

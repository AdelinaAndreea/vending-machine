package com.sda;

import java.util.*;

//HashMap-stocheaza fara ordine!-INTERVIU
//LinkedHashMap-pastreaza ordine in care punem noi
//TreeMap-sorteaza pe baza cheilor
public class CoinBox {
    private Map<Coin,List<Coin>> vault;

    public CoinBox() {
        this.vault=new LinkedHashMap<>();
        vault.put(Coin.CINCIZECI_BANI,new ArrayList<>()); //aloc memorie pt 50 bani
        vault.put(Coin.UN_LEU,new ArrayList<>());
        vault.put(Coin.CINCI_LEI,new ArrayList<>());
        vault.put(Coin.ZECE_LEI,new ArrayList<>());
    }

    public void add(Coin coin){
        List<Coin> coins=vault.get(coin);  //returneaza o lista de coins
        coins.add(coin);
    }

    public Map<Coin, List<Coin>> getVault() {
        return vault;
    }

    public Optional<Coin> getCoinFromVault(Coin coin){ //sectiune din sertar cu bancnota de acelasi tip.sertar cu 5 lei. optional=poate nu sunt bani de 5 lei
        List<Coin> coins =vault.get(coin);
        if(coins.isEmpty()){
            return Optional.empty();  //index 0=nu exista=>out of bounds
        }
       return Optional.of(coins.remove(0));

    }

    public void add(List<Coin> coins){
        for(Coin coin:coins){
            add(coin);
        }
    }
}

package com.sda;

import java.util.Optional;

//cand instantiez enumul tre dat nume si valoare!
public enum Coin {
    CINCIZECI_BANI("50 de bani",0.5F,"50BANI"),
    UN_LEU("1 leu",1F,"1LEU"),
    CINCI_LEI("5 lei",5F,"5LEI"),
    ZECE_LEI("10 lei",10F,"10LEI");

    private String name;
    private float value;
    private String code;

    Coin(String name, float value, String code) {
        this.name = name;
        this.value = value;
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }
    //met este statica=poate fi apelata fara sa fie instantiat obiectul
    public static Optional<Coin> getCoinByCode(String coinCode) {  // metoda=contract=>neaparat tre sa intoarca Product!! nu returneaza null!!
        for (Coin coin : Coin.values()) {
            if(coin.getCode().equals(coinCode)){
                return Optional.of(coin);
            }
        }
        return Optional.empty(); //returneaza cutia goala!
    }

    public static Coin getCoinByLowerOrEqualValue(float value){
        Coin maxCoinLowerOrEqualWithValue=CINCIZECI_BANI;  //cea mai mare valoare <=cu restul?
        for(Coin coin:Coin.values()){
            if(coin.getValue()<=value){
                if(maxCoinLowerOrEqualWithValue.getValue()< coin.getValue()){
                    maxCoinLowerOrEqualWithValue=coin;
                }
            }
        }
        return maxCoinLowerOrEqualWithValue;
    }
}

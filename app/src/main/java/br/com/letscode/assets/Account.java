package br.com.letscode.assets;

import br.com.letscode.enumerate.OperatoinTypes;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class Account {
    private final String id;
    private final String bank;
    private final String agency;
    private final String numberAccount;
    private Double balance;
    private final Set<Operations> operations = new HashSet<>();

    public Account(String[] header, String[] info){
        List<String> list = Arrays.asList(header);
        this.id = info[list.indexOf("ID_DA_CONTA")];
        this.bank = info[list.indexOf("NOME_DO_BANCO")];
        this.agency = info[list.indexOf("NUMERO_DA_AGENCIA")];
        this.numberAccount = info[list.indexOf("NUMERO_DA_CONTA")];
        this.balance = 0.0;
        this.addOperation(header, info);
    }

    public void addOperation(String[] header, String[] info){
        List<String> list = Arrays.asList(header);
        this.operations.add(new Operations(info[list.indexOf("DATAHORAOPERACAO")], info[list.indexOf("TIPO")], info[list.indexOf("VALOR")], info[list.indexOf("OPERADOR")]));
        newBalance();
    }

    public void newBalance(){
        this.balance=0.0;
        int i=0;
        for (Operations operation : this.operations) {
            i++;
            switch (operation.getOperationType().toUpperCase()){
                case "SAQUE":
                    this.balance-=operation.getValue();
                    break;
                case "DEPOSITO":
                    this.balance+=operation.getValue();
                    break;
                default:
            }
//            System.out.println(operation.toString());
        }
//        System.out.println(i);
//        System.out.println(this.balance);
    }

    public void printExtract(){


    }


}

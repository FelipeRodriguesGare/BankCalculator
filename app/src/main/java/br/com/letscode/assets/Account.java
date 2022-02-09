package br.com.letscode.assets;

import lombok.Getter;

import java.util.*;

@Getter
public class Account {
    private final String id;
    private final String bank;
    private final String agency;
    private final String numberAccount;
    private Double balance;
    private final Set<Operations> operations = new TreeSet<>();

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
        for (Operations operation : this.operations) {
            switch (operation.getOperationType().toUpperCase()){
                case "SAQUE":
                    this.balance-=operation.getValue();
                    break;
                case "DEPOSITO":
                    this.balance+=operation.getValue();
                    break;
                default:
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(String.format("%1$" + 39 + "s", " EXTRATO BANCARIO ").replace(' ', '=')).append(String.format("%1$" + 22 + "s", "=").replace(' ', '=')).append("\n\n");
        text.append(String.format("%1$" + -7 + "s", "BANCO").replace(' ', '_')).append(String.format("%1$" + 20 + "s", this.bank).replace(' ', '_')).append("\n");
        text.append(String.format("%1$" + -7 + "s", "AGÊNCIA").replace(' ', '_')).append(String.format("%1$" + 20 + "s", this.agency).replace(' ', '_')).append("\n");
        text.append(String.format("%1$" + -7 + "s", "CONTA").replace(' ', '_')).append(String.format("%1$" + 20 + "s", this.numberAccount).replace(' ', '_')).append("\n\n");

        text.append(String.format("%1$" + 11 + "s", "Data")).append(String.format("%1$" + 21 + "s", "Tipo")).append(String.format("%1$" + 15 + "s", "Valor")).append(String.format("%1$" + 15 + "s", "Operador\n"));
        text.append(String.format("%1$" + 61 + "s", "-").replace(' ', '-')).append("\n");
        for (Operations operation : this.operations){
            text.append(operation.getOperationDate()).append((" ").repeat(Math.max(0,7)));
            text.append(String.format("%1$" + -15 + "s", operation.getOperationType()));
            char character = operation.getOperationType().equalsIgnoreCase("SAQUE") ? '-':'+';
            text.append(character).append(String.format("%1$" + 6 + "s", operation.getValue()));
            text.append(String.format("%1$" + 6 + "s", " ")).append(String.format("%1$" + -20 + "s", operation.getOperator())).append("\n");
        }

        text.append("\nSaldo").append(String.format("%1$" + 55 + "s", this.balance).replace(' ', '_'));

        return text.toString();
    }
}

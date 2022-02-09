package br.com.letscode.assets;

import lombok.Getter;
import org.w3c.dom.Text;

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
        }
    }

    @Override
    public String toString() {
        int size = 45;
        StringBuilder text = new StringBuilder();
        text.append("BANCO ").append(this.bank).append("\n");
        text.append("AGÊNCIA ").append(this.agency).append("\n");
        text.append("CONTA ").append(this.numberAccount).append("\n\n");

        text.append(String.format("%1$" + 11 + "s", "Data")).append(String.format("%1$" + 21 + "s", "Tipo")).append(String.format("%1$" + 15 + "s", "Valor")).append(String.format("%1$" + 15 + "s", "Operador\n"));
        for (Operations operation : this.operations){
            text.append(operation.getOperationDate()).append((" ").repeat(Math.max(0,7)));
            text.append(String.format("%1$" + -15 + "s", operation.getOperationType()));
            text.append(String.format("%1$" + 6 + "s", operation.getValue()));
            text.append(String.format("%1$" + 13 + "s", operation.getOperator())).append("\n");
        }

        text.append("\n\nSaldo ").append(String.format("%1$" + 54 + "s", this.balance));

        return text.toString();
    }
}

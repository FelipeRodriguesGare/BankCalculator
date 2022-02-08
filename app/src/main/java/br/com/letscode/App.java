package br.com.letscode;

import br.com.letscode.assets.Account;
import br.com.letscode.assets.ManageAccounts;
import br.com.letscode.input_output.ReadFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {
        URL res = App.class.getClassLoader().getResource("operacoes.csv");

        File file = null;
        try {
            assert res != null;
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert file != null;

        ReadFile reader = new ReadFile(file.getAbsolutePath());

        ManageAccounts manage = new ManageAccounts();
        manage.menageAccounts(reader.readCSV());
        Map<String, Account> accounts = manage.getAccounts();

        for (Map.Entry<String,Account> entry : accounts.entrySet()){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
//        accounts.get("148972c2-7062-40c3-9b4d-e96b538a90dc").newBalance();

    }
}

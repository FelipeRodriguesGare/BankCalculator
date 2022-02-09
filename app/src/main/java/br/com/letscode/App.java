package br.com.letscode;

import br.com.letscode.assets.Account;
import br.com.letscode.assets.ManageAccounts;
import br.com.letscode.input_output.FileManipulation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
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

        FileManipulation reader = new FileManipulation(file.getAbsolutePath());

        ManageAccounts manage = new ManageAccounts();
        manage.menageAccounts(reader.readCSV());
        Map<String, Account> accounts = manage.getAccounts();

        for (Map.Entry<String,Account> entry : accounts.entrySet()){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            reader.exportTxt(entry.getValue().toString(),entry.getKey());
        }

    }
}

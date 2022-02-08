package br.com.letscode.assets;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ManageAccounts {
    Map<String, Account> accounts = new HashMap<>();

    public void menageAccounts(List<String[]> file){
        List<String> header = Arrays.asList(file.get(0));
        for (int i = 1; i < file.toArray().length; i++){
            String id = file.get(i)[header.indexOf("ID_DA_CONTA")];
            if (this.accounts.get(id) == null){
                this.accounts.put(id,new Account(file.get(0),file.get(i)));
            } else{
                this.accounts.get(id).addOperation(file.get(0), file.get(i));
            }
        }
    }
}

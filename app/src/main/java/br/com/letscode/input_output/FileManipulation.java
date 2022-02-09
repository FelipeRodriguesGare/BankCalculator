package br.com.letscode.input_output;

import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileManipulation {
    private String filePath = "";

    public List<String[]> readCSV() throws IOException {
        List<String[]> lines;

        try (CSVReader reader = new CSVReader(new FileReader(this.filePath,Charset.defaultCharset()))) {
            lines = reader.readAll();
        }
        return lines;
    }

    public void exportTxt(String stringToExport,String id){
        try {
            new File("Extrato").mkdir();
            Path filePath = Paths.get("Extrato\\" + id + ".txt");
            Files.writeString(filePath, stringToExport, Charset.defaultCharset(), CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

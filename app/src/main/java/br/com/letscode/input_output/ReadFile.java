package br.com.letscode.input_output;

import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadFile {
    String filePath = "";

    public List<String[]> readCSV() throws IOException {
        List<String[]> lines;
        try (CSVReader reader = new CSVReader(new FileReader(this.filePath))) {
            lines = reader.readAll();
        }
        return lines;
    }

}

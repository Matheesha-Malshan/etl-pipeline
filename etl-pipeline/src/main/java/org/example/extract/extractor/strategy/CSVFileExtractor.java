package org.example.extract.extractor.strategy;

import org.example.extract.extractor.FileExtractor;
import org.example.model.Filetype;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CSVFileExtractor implements FileExtractor {


    @Override
    public ArrayList<Map<String,String>> readFile(File file) throws IOException {
        return readCSV(file);
    }

    public ArrayList<Map<String,String>> readCSV(File file) throws IOException {

        ArrayList<Map<String, String>> rows=new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String headline= br.readLine();

        if (headline==null)return rows;

        String[] headlines=headline.split(",");

        String lineOne;
        while ((lineOne=br.readLine())!=null){
            String[] values=lineOne.split(",");

            HashMap<String,String> hashMap=new HashMap<>();

            for(int i=0;i<headlines.length;i++){
                hashMap.put(headlines[i],values[i]);
            }
            rows.add(hashMap);
        }
        return rows;

    }

    @Override
    public boolean checkType(Filetype filetype) {
        return filetype==Filetype.CSV;
    }
}

package org.example.extract.extractor.strategy;

import org.example.extract.extractor.FileExtractor;
import org.example.model.Filetype;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

@Service
public class JsonFileExtractor implements FileExtractor {
    @Override
    public ArrayList<Map<String,String>> readFile(File file){
        return null;
    }

    public ArrayList<Map<String,String>>readJson(File file){

        return  null;
    }


    @Override
    public boolean checkType(Filetype filetype) {
        return filetype==Filetype.JSON;
    }

}

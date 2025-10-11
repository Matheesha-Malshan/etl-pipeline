package org.example.extract.extractor.extractorContext.extractorContextImpl;

import org.example.extract.extractor.FileExtractor;
import org.example.extract.extractor.extractorContext.ExtractorFactory;
import org.example.model.Filetype;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ExtractorFactoryImpl implements ExtractorFactory {

    HashMap<Filetype,FileExtractor> map=new HashMap<>();

    ExtractorFactoryImpl(List<FileExtractor> fileExtractors){

       for(FileExtractor fileExtractor:fileExtractors){
           for (Filetype filetype:Filetype.values()){
                if(fileExtractor.checkType(filetype)){
                    map.put(filetype,fileExtractor);
                }
           }
       }

    }
    public FileExtractor getStrategy(Filetype filetype){
        return map.get(filetype);
    }

    public void readStrategy(){

    }

}

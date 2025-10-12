package org.example.transformer.parser.parserImpl;

import org.example.model.Filetype;
import org.example.transformer.parser.ParserService;
import org.springframework.stereotype.Component;

@Component
public class CSVParser implements ParserService {


    public boolean checkType(Filetype filetype){
        return Filetype.CSV==filetype;
    }

    @Override
    public Object handleData(String data) {
        return null;
    }
}

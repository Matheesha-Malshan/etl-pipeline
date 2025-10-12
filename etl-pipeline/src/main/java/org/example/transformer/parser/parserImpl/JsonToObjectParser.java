package org.example.transformer.parser.parserImpl;

import lombok.RequiredArgsConstructor;
import org.example.model.Filetype;
import org.example.model.SalesTransaction;
import org.example.transformer.parser.ParserService;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class JsonToObjectParser implements ParserService {

    final ObjectMapper objectMapper;

    public Object handleData(String data) {
        return objectMapper.readValue(data,SalesTransaction.class);
    }
    public boolean checkType(Filetype filetype){
        return Filetype.JSON==filetype;
    }
}

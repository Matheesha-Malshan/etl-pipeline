package org.example.transformer.chain.chainImpl;

import lombok.RequiredArgsConstructor;
import org.example.model.Filetype;
import org.example.transformer.chain.TransformationHandler;
import org.example.transformer.parser.parserImpl.ParserFactoryImpl;
import org.example.transformer.parser.ParserService;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ParseHandler extends TransformationHandler {

    final ParserFactoryImpl parserFactory;

    @Override
    public Object process(Object input) {

        ParserService parserService=parserFactory.getObject(Filetype.JSON);
        String raw=(String)input;

        return parserService.handleData(raw);


    }
}

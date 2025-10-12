package org.example.transformer.parser;

import org.example.model.Filetype;

public interface ParserService {
    boolean checkType(Filetype filetype);
    Object handleData(String data);
}

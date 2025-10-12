package org.example.transformer.parser;

import org.example.model.Filetype;

public interface ParserFactory {
    ParserService getObject(Filetype filetype);
}

package org.example.extract.extractor.extractorContext;

import org.example.extract.extractor.FileExtractor;
import org.example.model.Filetype;

public interface ExtractorFactory {
    FileExtractor getStrategy(Filetype filetype);
}

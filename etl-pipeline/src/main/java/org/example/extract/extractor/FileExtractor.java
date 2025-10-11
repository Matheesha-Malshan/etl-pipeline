package org.example.extract.extractor;

import org.example.model.Filetype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface FileExtractor {

    ArrayList<Map<String,String>> readFile(File file) throws IOException;

    boolean checkType(Filetype filetype);
}

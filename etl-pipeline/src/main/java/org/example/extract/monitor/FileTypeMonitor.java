package org.example.extract.monitor;

import org.example.model.Filetype;

import java.nio.file.Path;

public interface FileTypeMonitor {
    Filetype detectFileType(Path filepath);
}

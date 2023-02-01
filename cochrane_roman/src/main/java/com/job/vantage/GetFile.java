package com.job.vantage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class GetFile {

    private String fileName;

    public GetFile(String fileName) {
        this.fileName = fileName;
    }

    public BufferedWriter create_file() throws FileNotFoundException {
        
        File fout = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fout);
        return new BufferedWriter(new OutputStreamWriter(fos));
    }
}
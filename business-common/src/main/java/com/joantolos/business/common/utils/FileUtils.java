package com.joantolos.business.common.utils;

import com.joantolos.business.common.exception.FileManipulationException;

import java.io.*;

/**
 *
 * Created by jtolos on 13/01/2015.
 */
public interface FileUtils {

    public InputStream byteArrayToInputStream(byte[] byteArray);

    public void closeInputStream(InputStream is) throws FileManipulationException;

    public void closeBufferedReader(BufferedReader br) throws FileManipulationException;

    public String streamToString(InputStream inputStream) throws FileManipulationException;

    public byte[] streamToByteArray(InputStream inputStream) throws FileManipulationException;

    public void writeFile(byte[] byteArray, String outputPath) throws FileManipulationException;

    public void writeFile(InputStream inputStream, String outputPath) throws FileManipulationException;

    public void deleteFile(String path);

    public void logFile(String path);

    public boolean fileExist(String path);

    public InputStream stringToInputStream(String inputStream) throws FileManipulationException;
}

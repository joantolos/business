package com.joantolos.business.common.utils.impl;

import com.joantolos.business.common.utils.FileUtils;
import com.joantolos.business.common.enums.FileErrorCode;
import com.joantolos.business.common.exception.FileManipulationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class FileUtilsImpl implements FileUtils {

    private Logger logger = LoggerFactory.getLogger(FileUtilsImpl.class);

    public InputStream byteArrayToInputStream(byte[] byteArray){
        return new ByteArrayInputStream(byteArray);
    }
   
    public void closeInputStream(InputStream is) throws FileManipulationException {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                throw new FileManipulationException(FileErrorCode.CLOSING_INPUT_STREAM_ERROR);
            }
        }
    }

    public void closeBufferedReader(BufferedReader br) throws FileManipulationException {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                throw new FileManipulationException(FileErrorCode.CLOSING_BUFFERED_READER_ERROR);
            }
        }
    }

    public String streamToString(InputStream inputStream) throws FileManipulationException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new FileManipulationException(FileErrorCode.STRING_FROM_STREAM_ERROR);
        } finally {
            closeBufferedReader(br);
        }

        return sb.toString();
    }

    public byte[] streamToByteArray(InputStream inputStream) throws FileManipulationException {
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new FileManipulationException(FileErrorCode.STRING_FROM_STREAM_ERROR);
        }
    }

    public void writeFile(byte[] byteArray, String outputPath) throws FileManipulationException{
        this.writeFile(this.byteArrayToInputStream(byteArray), outputPath);
    }

    public void writeFile(InputStream inputStream, String outputPath) throws FileManipulationException {
        try{
            OutputStream outputStream = new FileOutputStream(new File(outputPath));

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.close();
        }catch( IOException e){
            throw new FileManipulationException(FileErrorCode.WRITING_FILE_ERROR);
        }
    }

    public void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }

    public void logFile(String path){
        logger.info("Trying to open file: "+path+" ... ");
        System.out.println("Trying to open file: "+path+" ... ");
        File file = new File(path);
        if(file.exists()){
            logger.info("*** FILE EXISTS ***");
            logger.info("File name: "+file.getName());
            logger.info("File absolute path: "+file.getAbsolutePath());
            logger.info("File size: "+file.length());
        }else{
            logger.info("*** FILE DON'T EXISTS ***");
        }
    }

    public boolean fileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public InputStream stringToInputStream(String inputStream) throws FileManipulationException {
        return new ByteArrayInputStream(inputStream.getBytes(StandardCharsets.UTF_8));
    }
}

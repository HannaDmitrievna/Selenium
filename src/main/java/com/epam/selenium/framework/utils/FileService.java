package com.epam.selenium.framework.utils;

import com.epam.selenium.framework.reporting.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {
    public static final String PATH_TO_UPLOAD_FILE_DIRECTORY = FileUtils.getTempDirectoryPath();
    public static final String PATH_FOR_DOWNLOADING = FileUtils.getTempDirectoryPath() + "download\\";

    public static String createFile() {
        Logger.info("Creating of the file");
        String fileName = RandomStringUtils.randomAlphabetic(5) + ".txt";
        File file = new File(PATH_TO_UPLOAD_FILE_DIRECTORY, fileName);
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(RandomStringUtils.randomAlphabetic(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fileName;
    }

    public static void deleteFile(String file) {
        Logger.info("Deleting of file: " + file);
        try {
            Files.delete(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFilesEquals(String uploadedFile, String downloadedFile) throws Exception {
        Logger.info("Checking is file " + uploadedFile + " and file " + downloadedFile + " are equals");
        FileInputStream uploadedFileStream = new FileInputStream(uploadedFile);
        String md5Uploaded = org.apache.commons.codec.digest.DigestUtils.md5Hex(uploadedFileStream);
        uploadedFileStream.close();
        FileInputStream downloadedFileStream = new FileInputStream(downloadedFile);
        String md5Downloaded = org.apache.commons.codec.digest.DigestUtils.md5Hex(downloadedFileStream);
        downloadedFileStream.close();
        return md5Uploaded.equals(md5Downloaded);
    }
}

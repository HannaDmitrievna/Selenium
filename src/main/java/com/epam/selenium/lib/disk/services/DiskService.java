package com.epam.selenium.lib.disk.services;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.lib.disk.screens.DiskPage;
import com.epam.selenium.lib.disk.screens.TrashPage;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

import static com.epam.selenium.framework.ui.Browser.current;

public class DiskService {
    public static void uploadFile(String fileName) {
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        Logger.info("Uploading: " + fileName);
        diskPage.attachFile(fileName);
        diskPage.waitUploadForNotification();
        diskPage.closePopup();
        diskPage.open();
    }

    public static void downloadFile(String fileName) {
        Logger.info("Downloading: " + fileName);
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        diskPage.open();
        diskPage.chooseFile(fileName);
        diskPage.downloadFile();
    }

    public static void waitDownloadingFile(File file) {
        Logger.info("Waiting downloading file");
        while (!file.canWrite()) ;
    }

    public static boolean isFilePresentAtDisk(String fileName) {
        Logger.info("Checking presenting of file at disk: " + fileName);
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        diskPage.open();
        return diskPage.isFilePresent(fileName);
    }

    public static void deleteFileToTrash(String fileName) {
        Logger.info("Deleting file " + fileName + " to trash");
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        diskPage.refresh();
        diskPage.open();
        diskPage.chooseFile(fileName);
        diskPage.deleteFile();
        diskPage.waitDeleteNotification();
    }

    public static boolean isFilePresentInTrash(String fileName) {
        Logger.info("Checking presenting of file at trash: " + fileName);
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        diskPage.open();
        diskPage.refresh();
        TrashPage trashPage = PageFactory.initElements(current().getWrappedDriver(), TrashPage.class);
        trashPage.open();
        trashPage.refresh();
        trashPage.open();
        return trashPage.isFilePresent(fileName);
    }

    public static void clearTrash() {
        Logger.info("Clearing trash");
        TrashPage trashPage = PageFactory.initElements(current().getWrappedDriver(), TrashPage.class);
        trashPage.clickOnClearTrash();
        trashPage.confirmClearTrash();
    }

    public static void deleteFilesToTrash(List<String> fileNames) {
        Logger.info("Deleting files to trash");
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        diskPage.chooseFiles(fileNames);
        diskPage.deleteFiles();
        diskPage.waitDeleteNotification();
    }

    public static void restoreFile(String fileName) {
        Logger.info("Restoring of file: " + fileName);
        TrashPage trashPage = PageFactory.initElements(current().getWrappedDriver(), TrashPage.class);
        trashPage.chooseFile(fileName);
        trashPage.restoreFile(fileName);
        trashPage.waitRestoring();
        DiskPage diskPage = PageFactory.initElements(current().getWrappedDriver(), DiskPage.class);
        diskPage.open();
        diskPage.refresh();
        diskPage.open();
    }
}
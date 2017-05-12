package com.epam.selenium.tests.disk;

import com.epam.selenium.framework.utils.FileService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.selenium.framework.utils.FileService.PATH_TO_UPLOAD_FILE_DIRECTORY;
import static com.epam.selenium.lib.disk.services.DiskService.isFilePresentAtDisk;
import static com.epam.selenium.lib.disk.services.DiskService.uploadFile;

public class UploadFileTest extends BaseDiskTest {
    protected String fileName;

    @BeforeMethod(description = "Create file")
    public void createFile() {
        fileName = FileService.createFile();
    }

    @Test(description = "Upload TestParameters", priority = 1)
    public void checkUploadFile() throws InterruptedException {
        uploadFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
        Assert.assertTrue(isFilePresentAtDisk(fileName));
    }

    @AfterMethod(description = "Deleting file")
    public void deleteFile() {
        FileService.deleteFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }
}
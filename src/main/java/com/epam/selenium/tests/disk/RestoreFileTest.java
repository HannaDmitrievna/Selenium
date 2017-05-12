package com.epam.selenium.tests.disk;

import com.epam.selenium.framework.utils.FileService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.selenium.framework.utils.FileService.PATH_TO_UPLOAD_FILE_DIRECTORY;
import static com.epam.selenium.lib.disk.services.DiskService.*;

public class RestoreFileTest extends BaseDiskTest {

    private String fileName;

    @BeforeClass(description = "Create file")
    public void createFile() {
        fileName = FileService.createFile();
        uploadFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }

    @Test(description = "Restore TestParameters", priority = 1)
    public void checkRestoringFile() throws InterruptedException {
        Assert.assertTrue(isFilePresentAtDisk(fileName));
        deleteFileToTrash(fileName);
        Assert.assertTrue(isFilePresentInTrash(fileName));
        restoreFile(fileName);
        Assert.assertTrue(isFilePresentAtDisk(fileName));
    }

    @AfterMethod(description = "Deleting file")
    public void deleteFile() {
        FileService.deleteFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }
}
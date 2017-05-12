package com.epam.selenium.tests.disk;

import com.epam.selenium.framework.utils.FileService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.selenium.framework.utils.FileService.PATH_TO_UPLOAD_FILE_DIRECTORY;
import static com.epam.selenium.lib.disk.services.DiskService.*;

public class RemoveFileTest extends BaseDiskTest {

    private String fileName;

    @BeforeClass(description = "Create file")
    public void createFile() {
        fileName = FileService.createFile();
        uploadFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }

    @Test(description = "Remove TestParameters", priority = 1)
    public void checkRemovingFile() throws InterruptedException {
        deleteFileToTrash(fileName);
        Assert.assertFalse(isFilePresentAtDisk(fileName));
        Assert.assertTrue(isFilePresentInTrash(fileName));
        clearTrash();
        Assert.assertFalse(isFilePresentInTrash(fileName));
    }

    @AfterMethod(description = "Deleting file")
    public void deleteFile() {
        FileService.deleteFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }
}
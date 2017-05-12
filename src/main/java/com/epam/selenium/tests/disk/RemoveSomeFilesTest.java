package com.epam.selenium.tests.disk;

import com.epam.selenium.framework.utils.FileService;
import com.epam.selenium.lib.disk.services.DiskService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class RemoveSomeFilesTest extends BaseDiskTest {

    private List<String> fileNameList = new LinkedList<>();

    @BeforeClass(description = "Create file")
    public void createFile() {
        for (int i = 0; i < 3; i++) {
            fileNameList.add(FileService.createFile());
        }
    }

    @Test(description = "Remove Some Files TestParameters", priority = 1)
    public void checkRemovingSomeFiles() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            DiskService.uploadFile(FileService.PATH_TO_UPLOAD_FILE_DIRECTORY + fileNameList.get(i));
            Assert.assertTrue(DiskService.isFilePresentAtDisk(fileNameList.get(i)));
        }
        DiskService.deleteFilesToTrash(fileNameList);
        for (int i = 0; i < 3; i++) {
            Assert.assertTrue(DiskService.isFilePresentInTrash(fileNameList.get(i)));
        }
    }

    @AfterMethod(description = "Deleting file")
    public void deleteFile() {
        for (int i = 0; i < 3; i++)
            FileService.deleteFile(FileService.PATH_TO_UPLOAD_FILE_DIRECTORY + fileNameList.get(i));
    }
}
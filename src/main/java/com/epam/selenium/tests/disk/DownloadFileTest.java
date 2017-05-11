package com.epam.selenium.tests.disk;

import com.epam.selenium.framework.utils.FileService;
import com.epam.selenium.lib.disk.services.DiskService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static com.epam.selenium.framework.utils.FileService.PATH_TO_UPLOAD_FILE_DIRECTORY;
import static com.epam.selenium.lib.disk.services.DiskService.uploadFile;

public class DownloadFileTest extends BaseDiskTest {

    protected String fileName;

    @BeforeClass(description = "Create and file")
    public void createAndUploadFile() {
        fileName = FileService.createFile();
        uploadFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }

    @Test(description = "Download TestParameters")
    public void checkDownloadFile() throws Exception {
        DiskService.downloadFile(fileName);
        DiskService.waitDownloadingFile(new File(FileService.PATH_FOR_DOWNLOADING + fileName));
        Assert.assertTrue(FileService.isFilesEquals(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName,
                FileService.PATH_FOR_DOWNLOADING + fileName));
    }

    @AfterClass(description = "Deleting file")
    public void deleteFile() {
        FileService.deleteFile(FileService.PATH_FOR_DOWNLOADING + fileName);
        FileService.deleteFile(PATH_TO_UPLOAD_FILE_DIRECTORY + fileName);
    }
}
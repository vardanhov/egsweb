package com.egswebapp.egsweb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.codehaus.janino.IClass;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.egswebapp.egsweb.util.DataFolderPath.DATA_FOLDER_PATH;

@Component
@Slf4j
public class FileUtil {


    private FileUtil() {
    }

    /**
     * Get File by file name
     *
     * @param fileName the {@Link fileName} id of post
     */
    private File getFile(final String fileName, final Constant packageName) {
        String filePath = DATA_FOLDER_PATH + File.separator + packageName.getPackageName() + File.separator + fileName;
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        return file;
    }


    /**
     * Get file name
     */
    public String getFileName(final MultipartFile files, final Constant constant) {
        final String uuid = UUID.randomUUID().toString();
        final File file = getFile(uuid + files.getOriginalFilename(), constant);
        try {
            FileUtils.writeByteArrayToFile(file, files.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    /**
     * delete file
     */
    public void deleteFile(final String fileName, final Constant packageName) {

        final String filePah = DATA_FOLDER_PATH + File.separator + packageName.getPackageName() + File.separator + fileName;
        if (fileName == null) {
            return;
        }
        File file = new File(filePah);
        if (file.delete()) {
            log.info("file is deleted");
        } else {
            log.error("file is not deleted");
        }

    }
    /**
     * upload files
     */

    public static MultipartFile[] uploadedFiles(MultipartFile[] files) {
        List<MultipartFile> list = new ArrayList<>();
        for (MultipartFile uploadedFile : files) {
            File file = new File(DATA_FOLDER_PATH + uploadedFile.getOriginalFilename());
            try {
                uploadedFile.transferTo(file);
                list.add(uploadedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MultipartFile[] multipartFiles = list.stream().toArray(MultipartFile[]::new);
        return multipartFiles;
    }

    /**
     * get img origin name
     */
    public String getOriginImagesName(final String imgName) {
        int firstIndex = imgName.indexOf("[");
        int lastIndex = imgName.lastIndexOf("]");
        return imgName.substring(firstIndex + 1, lastIndex);
    }


}

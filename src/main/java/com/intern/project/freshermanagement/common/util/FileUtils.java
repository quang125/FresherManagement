package com.intern.project.freshermanagement.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    public static String getFileName(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (StringUtils.isBlank(name)) {
            return "";
        }
        return name.substring(file.getOriginalFilename().replaceAll("\\\\", "/").lastIndexOf("/") + 1);
    }

    public static String getFileType(MultipartFile file) {
        String name = getFileName(file);
        return name.substring(name.lastIndexOf(".") + 1);
    }
}
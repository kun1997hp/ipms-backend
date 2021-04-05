package com.viettel.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class SQLBuilder {
    // Get SQL query in resources/sql/{module}/{queryId}.sql
    public static String getSqlQueryById(String module, String queryId) {
        InputStream inputStream = null;
        try {
            String filePath = "sql" + File.separator + module + File.separator + queryId + ".sql";
            log.info("SQL file path:" + filePath);
            Resource resource = new ClassPathResource(filePath);
            inputStream = resource.getInputStream();
            if (inputStream != null) {
//                return new String(inputStream.readAllBytes());
                byte[] bytes = IOUtils.toByteArray(inputStream);
                return new String(bytes);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
}

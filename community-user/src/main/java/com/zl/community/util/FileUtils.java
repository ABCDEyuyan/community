package com.zl.community.util;

import com.zl.community.config.SnowflakeConfig;

/**
 * @Author : ZL
 */
public class FileUtils {
    /**
     * 生成新的文件名，包含雪花 ID
     */
    public static String generateNewFilename(String originalFilename, SnowflakeConfig snowflakeConfig) {
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return originalFilename.substring(0, originalFilename.lastIndexOf(".")) + snowflakeConfig.snowflakeGenerator().next() + suffix;
    }
}

package org.ntvru.rucast.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.ntvru.rucast.model.FileDocument;

public class MimeTypeUtils {
private static final Tika tika = new Tika();
    
    private static Map<String, String> MimeMap;
    static
    {
        MimeMap = new HashMap<String, String>();
        MimeMap.put("mp4", "video/mp4");
        MimeMap.put("mp3", "audio/mp3");
        MimeMap.put("flv", "video/flv");
        MimeMap.put("webm", "video/webm");
        MimeMap.put("", "video/mp4");
    }

    public static String getMimeType(String extension) {
        if (extension.isEmpty())
            return "application/octet-stream";

        if (MimeMap.containsKey(extension)) {
            return MimeMap.get(extension);
        } else {
            return "unknown/" + extension;
        }
    }

    public static String getExtension(FileDocument item) {
        if (item.getFileType() != null) {
            return item.getFileType().replace("audio/", ".").replace("video/", ".");
        }
		return null;
        
//        if ("Youtube".equals(item.getPodcast().getType()) || item.getUrl().lastIndexOf(".") == -1 ) {
//            return ".mp4";
//        } else {
//            return "."+FilenameUtils.getExtension(item.getUrl());
//        }
    }
    
    // https://odoepner.wordpress.com/2013/07/29/transparently-improve-java-7-mime-type-recognition-with-apache-tika/
    public static String probeContentType(Path file) throws IOException {
        String mimetype = Files.probeContentType(file);
        if (mimetype != null) 
            return mimetype;

        mimetype = tika.detect(file.toFile());
        if (mimetype != null)
            return mimetype;
        
        return getMimeType(FilenameUtils.getExtension(String.valueOf(file.getFileName())));
    }
}

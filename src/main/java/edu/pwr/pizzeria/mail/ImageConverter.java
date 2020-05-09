package edu.pwr.pizzeria.mail;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Component
public class ImageConverter {

    private ResourceLoader resourceLoader;

    public ImageConverter(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String processImage(String image) {
        final Resource resource = resourceLoader.getResource("classpath:templates/images/" + image);
        try {
            final File imageFile = resource.getFile();

            if (!imageFile.exists()) {
                throw new RuntimeException(imageFile.getAbsolutePath());
            }

            byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

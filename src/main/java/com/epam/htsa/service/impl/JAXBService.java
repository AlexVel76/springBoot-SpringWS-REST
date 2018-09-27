package com.epam.htsa.service.impl;

import com.epam.htsa.entity.Item;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Service
public class JAXBService<T> {
    private static final String TEMP_DIR = "tempFiles";

    @Autowired
    @Qualifier("castorMarshaller")
    private Marshaller marshaller;
    @Autowired
    @Qualifier("castorMarshaller")
    private Unmarshaller unmarshaller;

    public byte[] convertFromEntityToXML(T entity, String fileName) throws FileNotFoundException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileOutputStream fos = null;
        try {
            File dir = getDir();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File localFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            fos = new FileOutputStream(localFile);
            StreamResult result = new StreamResult(outputStream);
            marshaller.marshal(entity, result);
            marshaller.marshal(entity, new StreamResult(fos));
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        return outputStream.toByteArray();
    }

    private File getDir() {
        String rootDir = System.getProperty("user.dir");
        File dir = new File(rootDir + File.separator + TEMP_DIR);
        return dir;
    }

    public Object convertFromXMLToEntity(InputStream inputStream, Class<T> clazz) throws IOException {
        try {
            return unmarshaller.unmarshal(new StreamSource(inputStream));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }
}

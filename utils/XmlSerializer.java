package app.utils;

import app.utils.exceptions.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component(value = "XmlSerializer")
public class XmlSerializer implements Serializer {

    private FileIO fileIo;

    @Autowired
    public XmlSerializer(FileIO fileIo) {
        this.fileIo = fileIo;
    }

    @Override
    public <T> void serialize(T obj, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            String path = System.getProperty("user.dir") + File.separator + fileName;
            File file = new File(path);
            if(!file.exists()){
                //create all directories if not exists
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try(OutputStream os = new FileOutputStream(fileName);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))){
                marshaller.marshal(obj, bw);
            }

        } catch (JAXBException e) {
            //log
            throw new SerializeException("Could not serialize " + obj, e);
        } catch (IOException e) {
            //log
            throw new SerializeException("Could not serialize. Unable to write to file " + fileName, e);
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T deserialize(Class<T> clazz, String fileName) {

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            try(InputStream is = clazz.getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))){

                //escape bad characters//
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null){
                    sb.append(line).append(System.lineSeparator());
                }
                String currentXml = sb.toString();
                currentXml = currentXml.trim().replaceFirst("^([\\W]+)<","<");
                String path = System.getProperty("user.dir") + "/src/main/resources" + fileName;

                this.fileIo.write(currentXml, path);
                File file = new File(path);

                T t = (T)unmarshaller.unmarshal(file);

                return t;
            }

        } catch (JAXBException e) {
            //log
            throw new SerializeException("Could not deserialize to class " + clazz, e);
        }catch (IOException e) {
            //log
            throw new SerializeException("Could not deserialize to class "
                    + clazz + ". Unable to read from file " + fileName, e);
        }
    }
}
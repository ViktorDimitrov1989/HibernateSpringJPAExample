package app.utils;

import app.utils.exceptions.SerializeException;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIO {
    public String read(String fileName){
        StringBuilder sb = new StringBuilder();


        try(InputStream is = this.getClass().getResourceAsStream(fileName)){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }

        } catch (IOException e) {
            throw new SerializeException(fileName + " cannot be read.", e);
        }

        return sb.toString();
    }


    public void write(String str, String fileName) throws FileNotFoundException {
        OutputStream os = new FileOutputStream(fileName);

        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))){
            bw.write(str);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
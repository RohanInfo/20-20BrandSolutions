package com.infocepts.casino.dao;

import java.io.*;
import java.security.SecureRandom;

public class TestMstr {

    public int createLoginUser(String userName) {

        int res = 0;

        try {

            ProcessBuilder processBuilder = new ProcessBuilder();
            String currentDir = System.getProperty("user.dir");


            String fileName = String.valueOf(Math.abs(new SecureRandom().nextLong()) + ".scp");


            System.out.println("currentDir : " + currentDir);
            System.out.println("fileName : " + fileName);


            PrintWriter printWriter = new PrintWriter(currentDir + "\\createUser\\" + fileName);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("EXECUTE PROCEDURE test (\"");
            stringBuilder.append(userName);
            stringBuilder.append("\");");


            System.out.println(stringBuilder);

            printWriter.println(stringBuilder);
            printWriter.close();

            processBuilder.command("createUser\\createUser.bat", "createUser\\" + fileName);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();

            InputStream inputStream = process.getInputStream();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                }
                //JOptionPane.showMessageDialog(null,sb);
            }


            System.out.println("Exit Value : " + process.exitValue());

            res=process.exitValue();
            File file = new File(currentDir + "//createUser//" + fileName);
            file.delete();

        } catch (FileNotFoundException fnfex) {

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        return res;
    }

}

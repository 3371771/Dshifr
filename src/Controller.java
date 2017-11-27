import com.sun.javafx.tk.Toolkit;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class Controller {

//    private static byte[] array_before;
//    private static byte[] array_after;
//
//
//    static void set_arr(byte[] array) {
//        array_before = array;
//    }
//
//    static byte[] get_arr() {
//        return array_after;
//    }


    static void RSA_zash() {
        System.out.println("зашифровка RSA");
    }

    static void DES_zash() {
        System.out.println("зашифровка DES");
    }

    static void AES_zash(int n) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {


        //тут деленеи на расшифровку и зашифровку
        Cipher cipher = Cipher.getInstance("AES");

        //KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        //SecretKey secKey = keyGen.generateKey();

        SecretKeySpec secKey = new SecretKeySpec("AES12345aes12345".getBytes(), "AES");

        String file_name = AlertWindow.file.getName();
        int index = file_name.lastIndexOf(".");

        if (n==1) {

            System.out.println("зашифровка AES");

            cipher.init(Cipher.ENCRYPT_MODE, secKey);

            String cleartextFile = AlertWindow.file.getAbsolutePath();
            String ciperFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index)  + "_зашифрован." + file_name.substring(file_name.lastIndexOf(".")+1);

            FileInputStream fis = new FileInputStream(cleartextFile);
            FileOutputStream fos = new FileOutputStream(ciperFile);
            System.out.println("Зашифровали " + cleartextFile);
            System.out.println("Вот сюда " + ciperFile);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);

            byte[] block = new byte[8];

            int i;

            while ((i = fis.read(block)) != -1) {
                cos.write(block, 0, i);
            }
            cos.close();
        } else if (n==2) {

            System.out.println("расшифровываем");

            cipher.init(Cipher.DECRYPT_MODE, secKey);

            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index)  + "_расшифрован." +  file_name.substring(file_name.lastIndexOf(".")+1);
            String ciperFile = AlertWindow.file.getAbsolutePath();

            FileOutputStream fos;
            FileInputStream fis;

            fis = new FileInputStream(ciperFile);
            System.out.println("Расшифровываем сюда" + cleartextAgainFile);
            System.out.println("Вот это " + ciperFile);

            CipherInputStream cis = new CipherInputStream(fis, cipher);
            fos = new FileOutputStream(cleartextAgainFile);

            byte[] block = new byte[8];

            int i;

            while ((i = cis.read(block)) != -1) {
                fos.write(block, 0, i);
            }

            fos.close();
        }
    }

    static void RSA_rassh() throws Exception {
        System.out.println("расшифровка RSA");
    }

    static void DES_rassh() {
        System.out.println("расшифровка DES");
    }
}






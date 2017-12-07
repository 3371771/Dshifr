import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.*;


public class Controller {

   // private static SecretKey secKey;


    static void DES(int n) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, ClassNotFoundException {

        Cipher cipher = Cipher.getInstance("DES");

        // System.out.println(secKey);

        String file_name = AlertWindow.file.getName();
        int index = file_name.lastIndexOf(".");
        int index2 = file_name.lastIndexOf("з");

        SecretKey secKey;
        if (n == 1) {

            System.out.println("зашифровка DES");

            // secKey = new SecretKeySpec("DES12345".getBytes(), "DES");

            //генерация ключа
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            secKey = keyGenerator.generateKey();

            //сериализация кея

            FileOutputStream fileOutputStream = new FileOutputStream("secKey");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(secKey);
            objectOutputStream.close();

            //

            cipher.init(Cipher.ENCRYPT_MODE, secKey);

            String cleartextFile = AlertWindow.file.getAbsolutePath();
            String ciperFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index) + "_зашифрован_DES." + file_name.substring(file_name.lastIndexOf(".") + 1);

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
        } else if (n == 2) {

            System.out.println("расшифровываем DES");

            FileInputStream fileInputStream = new FileInputStream("secKey");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            secKey = (SecretKey) objectInputStream.readObject();

            cipher.init(Cipher.DECRYPT_MODE, secKey);

            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index2) + "расшифрован_DES." + file_name.substring(file_name.lastIndexOf(".") + 1);
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

    static void AES(int n) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {


        //тут деленеи на расшифровку и зашифровку
        Cipher cipher = Cipher.getInstance("AES");

        //KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        //SecretKey secKey = keyGen.generateKey();

        SecretKeySpec secKey = new SecretKeySpec("AES12345aes12345".getBytes(), "AES");

        String file_name = AlertWindow.file.getName();
        int index = file_name.lastIndexOf(".");
        int index2 = file_name.lastIndexOf("з");

        if (n == 1) {

            System.out.println("зашифровка AES");

            cipher.init(Cipher.ENCRYPT_MODE, secKey);

            String cleartextFile = AlertWindow.file.getAbsolutePath();
            String ciperFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index) + "_зашифрован_AES." + file_name.substring(file_name.lastIndexOf(".") + 1);

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
        } else if (n == 2) {

            System.out.println("расшифровываем AES");

            cipher.init(Cipher.DECRYPT_MODE, secKey);

            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index) + "расшифрован_AES." + file_name.substring(file_name.lastIndexOf(".") + 1);
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

    static void RSA(int n) throws Exception {

        String file_name = AlertWindow.file.getName();
        int index = file_name.lastIndexOf(".");
        int index2 = file_name.lastIndexOf("з");

        Cipher cipher = Cipher.getInstance("RSA");

        KeyPairGenerator genPair = KeyPairGenerator.getInstance("RSA");
        KeyPair keys = genPair.generateKeyPair();
        Key pubKey = keys.getPublic();
        Key privKey = keys.getPrivate();


 //       if (n==1) {
            System.out.println("Зашифровка RSA");

            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            String cleartextFile = AlertWindow.file.getAbsolutePath();
            String ciperFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index) + "_зашифрован_RSA." + file_name.substring(file_name.lastIndexOf(".") + 1);

            FileInputStream fis = new FileInputStream(cleartextFile);
            FileOutputStream fos = new FileOutputStream(ciperFile);
            System.out.println("Зашифровали " + cleartextFile);
            System.out.println("Вот сюда " + ciperFile);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);

            byte[] block = new byte[56];

            int i;

            while ((i = fis.read(block)) != -1) {
                cos.write(block, 0, i);
            }
            cos.close();

  //      } else if (n==2) {
            System.out.println("расшифровка RSA");

        Cipher cipher1 = Cipher.getInstance("RSA");
            cipher1.init(Cipher.DECRYPT_MODE, privKey);

            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + file_name.substring(0, index2)  + "расшифрован_RSA." +  file_name.substring(file_name.lastIndexOf(".")+1);
            String ciperFile1 = AlertWindow.file.getAbsolutePath();

            FileOutputStream fos1;
            FileInputStream fis1;

            fis1 = new FileInputStream(ciperFile);
            System.out.println("Расшифровываем сюда" + cleartextAgainFile);
            System.out.println("Вот это " + ciperFile);

            CipherInputStream cis1 = new CipherInputStream(fis1, cipher1);
            fos1 = new FileOutputStream(cleartextAgainFile);

      //      byte[] block = new byte[8];

     //       int i;

            while ((i = cis1.read(block)) != -1) {
                fos1.write(block, 0, i);
            }

            fos1.close();

        }

    }
//}






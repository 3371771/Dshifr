import javax.crypto.*;
import java.io.*;
import java.security.*;


class Controller {

    private static Key pubKeyRSA;
    private static Key privKeyRSA;

    static void DES(int n) throws Exception {

        Cipher cipher = Cipher.getInstance("DES");
        SecretKey secKeyDES;

        String fileName = AlertWindow.file.getName();
        int indexEncrypt = fileName.lastIndexOf(".");
        int indexDecrypt = fileName.lastIndexOf("з");

        //разделение на зашифровку и расшифровку
        // сперва зашифровка
        if (n == 1) {

            System.out.println("зашифровка DES");

            //генерация ключа
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            secKeyDES = keyGenerator.generateKey();

            //сериализация ключа
            FileOutputStream fileOutputStream = new FileOutputStream("secKeyDES");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(secKeyDES);
            objectOutputStream.close();

            //указание, что хотим зашифровать
            cipher.init(Cipher.ENCRYPT_MODE, secKeyDES);

            //получаем путь к файлу, который будем шифровать
            String cleartextFile = AlertWindow.file.getAbsolutePath();
            //формируем имя файла, в который будем сохранять расшифроанный докумнет
            String cipherFile = AlertWindow.file.getParent() + "\\" + fileName.substring(0, indexEncrypt) + "_зашифрован_DES." + fileName.substring(fileName.lastIndexOf(".") + 1);

            // объявляем потоки
            FileInputStream fis = new FileInputStream(cleartextFile);
            FileOutputStream fos = new FileOutputStream(cipherFile);

            System.out.println("Зашифровали " + cleartextFile);
            System.out.println("Вот сюда " + cipherFile);

            // сохраняем полученный файл
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            byte[] block = new byte[8];
            int i;
            while ((i = fis.read(block)) != -1) {
                cos.write(block, 0, i);
            }
            // закрываем поток
            cos.close();

            // расшифровка
        } else if (n == 2) {

            System.out.println("расшифровываем DES");

            // достаем ключ из файла
            FileInputStream fileInputStream = new FileInputStream("secKeyDES");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            secKeyDES = (SecretKey) objectInputStream.readObject();

            //указание, что хотим расшифровать
            cipher.init(Cipher.DECRYPT_MODE, secKeyDES);

            //получаем путь к файлу, который будем расшифровывать
            String cipherFile = AlertWindow.file.getAbsolutePath();
            //формируем имя файла, в который будем сохранять расшифроанный докумнет
            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + fileName.substring(0, indexDecrypt) + "расшифрован_DES." + fileName.substring(fileName.lastIndexOf(".") + 1);

            // объявляем потоки
            FileOutputStream fos = new FileOutputStream(cleartextAgainFile);
            FileInputStream fis = new FileInputStream(cipherFile);

            System.out.println("Расшифровываем сюда" + cleartextAgainFile);
            System.out.println("Вот это " + cipherFile);

            // сохраняем полученный файл
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            byte[] block = new byte[8];
            int i;
            while ((i = cis.read(block)) != -1) {
                fos.write(block, 0, i);
            }
            fos.close();
        }
    }

    static void AES(int n) throws Exception {

        Cipher cipher = Cipher.getInstance("AES");
        SecretKey secKeyAES;

        String fileName = AlertWindow.file.getName();
        int indexEncrypt = fileName.lastIndexOf(".");
        int indexDecrypt = fileName.lastIndexOf("з");

        if (n == 1) {
            System.out.println("зашифровка AES");

            //генерируем ключ
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            secKeyAES = keyGen.generateKey();

            //сериализация ключа
            FileOutputStream fileOutputStream = new FileOutputStream("secKeyAES");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(secKeyAES);
            objectOutputStream.close();

            //указание, что хотим зашифровать
            cipher.init(Cipher.ENCRYPT_MODE, secKeyAES);

            //получаем путь к файлу, который будем зашифровывать
            String cleartextFile = AlertWindow.file.getAbsolutePath();

            //формируем имя файла, в который будем сохранять зашифроанный докумнет
            String cipherFile = AlertWindow.file.getParent() + "\\" + fileName.substring(0, indexEncrypt) + "_зашифрован_AES." + fileName.substring(fileName.lastIndexOf(".") + 1);

            // объявляем потоки
            FileInputStream fis = new FileInputStream(cleartextFile);
            FileOutputStream fos = new FileOutputStream(cipherFile);

            System.out.println("Зашифровали " + cleartextFile);
            System.out.println("Вот сюда " + cipherFile);

            // сохраняем полученный файл
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            byte[] block = new byte[8];
            int i;
            while ((i = fis.read(block)) != -1) {
                cos.write(block, 0, i);
            }
            cos.close();

        } else if (n == 2) {

            System.out.println("расшифровываем AES");

            // достаем ключ из файла
            FileInputStream fileInputStream = new FileInputStream("secKeyAES");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            secKeyAES = (SecretKey) objectInputStream.readObject();

            //указание, что хотим расшифровать
            cipher.init(Cipher.DECRYPT_MODE, secKeyAES);

            //получаем путь к файлу, который будем расшифровывать
            String cipherFile = AlertWindow.file.getAbsolutePath();

            //формируем имя файла, в который будем сохранять расшифроанный докумнет
            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + fileName.substring(0, indexDecrypt) + "расшифрован_AES." + fileName.substring(fileName.lastIndexOf(".") + 1);

            // объявляем потоки
            FileOutputStream fos = new FileOutputStream(cleartextAgainFile);
            FileInputStream fis = new FileInputStream(cipherFile);

            System.out.println("Расшифровываем сюда" + cleartextAgainFile);
            System.out.println("Вот это " + cipherFile);

            CipherInputStream cis = new CipherInputStream(fis, cipher);
            byte[] block = new byte[8];
            int i;
            while ((i = cis.read(block)) != -1) {
                fos.write(block, 0, i);
            }
            fos.close();
        }
    }

    static void RsaKayGen() throws Exception {

        KeyPairGenerator genPair = KeyPairGenerator.getInstance("RSA");
        KeyPair keys = genPair.generateKeyPair();
        pubKeyRSA = keys.getPublic();
        privKeyRSA = keys.getPrivate();
        System.out.println(privKeyRSA);
        System.out.println(pubKeyRSA);

        //записываем закрытый ключи в файл
        FileOutputStream fileOutputStreamPriv = new FileOutputStream("privKeyRSA");
        ObjectOutputStream objectOutputStreamPriv = new ObjectOutputStream(fileOutputStreamPriv);
        objectOutputStreamPriv.writeObject(privKeyRSA);
        objectOutputStreamPriv.close();

        FileOutputStream fileOutputStreamPub = new FileOutputStream("pubKeyRSA");
        ObjectOutputStream objectOutputStreamPub = new ObjectOutputStream(fileOutputStreamPub);
        objectOutputStreamPub.writeObject(pubKeyRSA);
        objectOutputStreamPub.close();
    }

    static void RSA(int n) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");

        String fileName = AlertWindow.file.getName();
        int indexEncrypt = fileName.lastIndexOf(".");
        int indexDecrypt = fileName.lastIndexOf("з");

        if (n==1) {

            System.out.println("Зашифровка RSA");
            //проверяем есть ли на ПК публичный ключ для шифроания файлов
            if (!new File("pubKeyRSA").exists()) {
                //если нет, генерим пару ключей и сохраняем
                //генерируем пару ключей (открытый и закрытый)
                try {
                    RsaKayGen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //если есть, берем из файла
                // достаем ключ из файла
                System.out.println("есть");
                FileInputStream fileInputStream = new FileInputStream("pubKeyRSA");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                pubKeyRSA = (Key) objectInputStream.readObject();
            }

            //указание, что хотим зашифровать
            cipher.init(Cipher.ENCRYPT_MODE, pubKeyRSA);

            String cleartextFile = AlertWindow.file.getAbsolutePath();
            String cipherFile = AlertWindow.file.getParent() + "\\" + fileName.substring(0, indexEncrypt) + "_зашифрован_RSA." + fileName.substring(fileName.lastIndexOf(".") + 1);

            FileInputStream fis = new FileInputStream(cleartextFile);
            FileOutputStream fos = new FileOutputStream(cipherFile);
            System.out.println("Зашифровали " + cleartextFile);
            System.out.println("Вот сюда " + cipherFile);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);

                byte[] block = new byte[8];
                int i;
                while ((i = fis.read(block)) != -1) {
                    cos.write(block, 0, i);
                }
                cos.close();

        } else if (n==2) {

            System.out.println("расшифровка RSA");

            // достаем ключ из файла
            FileInputStream fileInputStream = new FileInputStream("privKeyRSA");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            privKeyRSA = (Key) objectInputStream.readObject();
            System.out.println(privKeyRSA);

            //указание, что хотим расшифровать
            cipher.init(Cipher.DECRYPT_MODE, privKeyRSA);

            String cipherFile = AlertWindow.file.getAbsolutePath();
            String cleartextAgainFile = AlertWindow.file.getParent() + "\\" + fileName.substring(0, indexDecrypt)  + "расшифрован_RSA." +  fileName.substring(fileName.lastIndexOf(".")+1);

            FileOutputStream fos = new FileOutputStream(cleartextAgainFile);
            FileInputStream fis = new FileInputStream(cipherFile);

            System.out.println("Расшифровываем сюда" + cleartextAgainFile);
            System.out.println("Вот это " + cipherFile);

            CipherInputStream cis = new CipherInputStream(fis, cipher);

            byte[] block = new byte[8];
            int i;
            while ((i = cis.read(block)) != -1) {
                fos.write(block, 0, i);
            }
            fos.close();
        }
    }
}






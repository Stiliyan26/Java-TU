package Files;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        final String fileName = "vector.txt";
        final VectorFactory vectorFactory = new VectorFactory();

        Vector3D[] vectorArr = new Vector3D[2];

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ){
            for (int i = 0; i < vectorArr.length; i++) {
                writer.write("Enter x: ");
                writer.flush();
                String x = reader.readLine();

                writer.write("Enter y: ");
                writer.flush();
                String y = reader.readLine();

                writer.write("Enter z: ");
                writer.flush();
                String z = reader.readLine();

                String newStr = x + ';' + y + ';' + z;
                vectorArr[i] = vectorFactory.vectorFromString(newStr);
                writer.write(vectorArr[i].toString() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
             BufferedReader br = new BufferedReader(new FileReader(fileName));
        ) {
            bw.write(vectorArr[0].toString());
            bw.flush();

            System.out.println(br.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        final String vectorBinPath = "vector.bin";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(vectorBinPath));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(vectorBinPath));
        ) {
            oos.writeObject(vectorArr[1]);
            Vector3D vectorFromFile = (Vector3D)ois.readObject();
            System.out.println(vectorFromFile.toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

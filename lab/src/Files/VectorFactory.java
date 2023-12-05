package Files;

public class VectorFactory {
    public static Vector3D vectorFromString(String vectorInfo){
        String[] vectorParamsArr = vectorInfo.split(";");

        float x = Float.parseFloat(vectorParamsArr[0]);
        float y = Float.parseFloat(vectorParamsArr[1]);
        float z = Float.parseFloat(vectorParamsArr[2]);

        return new Vector3D(x, y, z);
    }
}

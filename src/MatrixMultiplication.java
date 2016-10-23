import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * класс для перемножения матриц
 *
 */
public class MatrixMultiplication {

    /**
     * перемножение матриц
     * @param matrixA первая матрица
     * @param matrixB вторая матрица
     * @return перемножение
     * note функция не проверяет размерности матриц для перемножения(A.rows == B.columns)
     */
    public static ArrayList<ArrayList<Integer>> matrixMultiplication(ArrayList<ArrayList<Integer>> matrixA, ArrayList<ArrayList<Integer>> matrixB){
        int rowsA = matrixA.size();
        int rowsB = matrixB.size();
        int columnsB = matrixB.get(0).size();

        ArrayList<ArrayList<Integer>> matrixC = new ArrayList<>();
        for (int i = 0; i < rowsA; i++) {
            ArrayList<Integer> col = new ArrayList<>();
            for (int j = 0; j < columnsB; j++) {
                Integer ij = 0;
                for (int k = 0; k < rowsB; k++) {
                    ij = ij + matrixA.get(i).get(k) * matrixB.get(k).get(j);
                }
                col.add(ij);
            }
            matrixC.add(col);
        }
        return matrixC;
    }



    /**
     * печать матрицы
     * @param matrix
     */
    public static void printMatrix(ArrayList<ArrayList<Integer>> matrix){
        NumberFormat formatter = new DecimalFormat("#0.00");

        for (int i =0; i< matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                System.out.print(formatter.format(matrix.get(i).get(j)) + " ");
            }
            System.out.println();
        }
    }

    /**
     * чтение матрицы из файла
     * @param fileName фай, в котором записана матрица
     * @return матрица, прочитання из файла
     * @throws FileNotFoundException
     */
    public static ArrayList<ArrayList<Integer>> readmatrixFromFile(String fileName) throws FileNotFoundException{

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        Scanner input = new Scanner(new File(fileName));
        while(input.hasNextLine())
        {
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList col = new ArrayList();
            while(colReader.hasNextInt())
            {
                col.add(colReader.nextInt());
            }
            matrix.add(col);
        }
        return matrix;
    }

    /**
     * @return корректность выволнения теста
     * @throws FileNotFoundException
     */
    public static boolean testMatrixMultiplication() throws FileNotFoundException{

        ArrayList<ArrayList<Integer>>  mA = readmatrixFromFile("res/matrixTestA");
        ArrayList<ArrayList<Integer>>  mB = readmatrixFromFile("res/matrixTestB");
        ArrayList<ArrayList<Integer>>  mC =  matrixMultiplication(mA,mB);

        // правильный ответ
        ArrayList<ArrayList<Integer>> correctTest = new ArrayList<>();
        ArrayList<Integer> correctTestRow0 = new ArrayList<Integer>(){{
            add(8);
        }};
        ArrayList<Integer> correctTestRow1 = new ArrayList<Integer>(){{
            add(-6);
        }};

        correctTest.add(correctTestRow0);
        correctTest.add(correctTestRow1);

        return mC.equals(correctTest);
    }

    /*
    *
    */
    public static void main(String[] args) throws FileNotFoundException {

        boolean passTests = testMatrixMultiplication();
        if(passTests){
            System.out.println("Running tests: true");
        }
        else{
            System.out.println("Running tests: false");
        }

        ArrayList<ArrayList<Integer>>  mA = readmatrixFromFile("res/matrixA");
        System.out.println("MatrixA");
        printMatrix(mA);

        ArrayList<ArrayList<Integer>>  mB = readmatrixFromFile("res/matrixB");
        System.out.println("MatrixB");
        printMatrix(mB);

        System.out.println("MatrixC = A*B");
        ArrayList<ArrayList<Integer>>  mC =  matrixMultiplication(mA,mB);
        printMatrix(mC);

    }
}
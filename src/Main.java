import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void generateFile(String filename,int size,int min, int max) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File(filename));
        Random rand = new Random();
        for(int i=0; i<size; i++){
            int x=rand.nextInt(max     -min)+min;
            out.print(x+" ");
        }
        out.close();
    }
    public static int[] getData(String filename,int x){
        Random rand = new Random();
        //int  x=10;//rand.nextInt(30)+10;

        try {
            generateFile(filename,x,-25,25);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] list=new int[x];
        BufferedReader bf=null;
        try {

            bf=new BufferedReader(new FileReader(filename));
            String line[]=bf.readLine().split(" ");
            for(int i=0; i<line.length; i++){
                list[i]=Integer.parseInt(line[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int [] copy(int[] list, int pos1, int pos2){
        int rez[]=new int[pos2-pos1];
        int k=0;
        for(int i=pos1; i<pos2; i++){
            rez[k]=list[i];k++;
        }
        return rez;
    }
    public static int getPosInAnotherList(int[] list,int el){
        int pos=list.length;
        for(int i=0; i<list.length; i++){
            if (el<=list[i]){
                pos=i;
                break;
            }
        }
        return pos;

    }
    public static int[] merge(int[] a, int[] b){
        int k=0;
        int[] merged=new int[a.length+b.length];
        int i=0,j=0;
        while(i!=a.length && j!=b.length){
            //System.out.println(i+" "+ j);
            if(a[i]<=b[j]){
                merged[k]=a[i];
                k++;
                i++;
            }else{
                merged[k]=b[j];
                k++;
                j++;
            }
        }
        if(i!=a.length){
            for(int ii=i; ii<a.length; ii++){
                merged[k]=a[ii];
                k++;
            }
        }
        if(j!=b.length){
            for(int jj=j; jj<b.length; jj++){
                merged[k]=b[jj];
                k++;
            }
        }
        return merged;
    }
    public static int[] mergeSortSecv(int[] list) {
        if (list.length == 1) {
            return list;
        } else {
            int m = list.length / 2;
          //  System.out.println(m + " m");
            int[] a = copy(list, 0, m);
            int[] b = copy(list, m, list.length);
            int[] aSorted = mergeSortSecv(a);
            int[] bSorted = mergeSortSecv(b);
            return merge(aSorted, bSorted);
        }
    }

     

    public static void main(String[] args) {
        int[] a=getData("C:\\Users\\Patcas\\IdeaProjects\\Lab3PPJava\\data.txt",25);
        int[] rez=mergeSortSecv(a);

        PrintWriter out = null;
        try {
            out = new PrintWriter(new File("C:\\Users\\Patcas\\IdeaProjects\\Lab3PPJava\\rez.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0; i<rez.length; i++){
           out.print(rez[i]+" ");
        }
        out.close();
    }
}

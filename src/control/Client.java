package control;

import model.Answer;
import model.ServerConfiguration;
import model.Student;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws IOException, NotBoundException, ClassNotFoundException {
        Student student = new Student("b17dccn315","N.N.Huy","192.168.1.123",3);
        //Get port
        int numericPort,stringPort,Code;
        Registry reg = LocateRegistry.getRegistry("localhost",4444);
        IRMIServer service = (IRMIServer) reg.lookup("Ca2_Server");
        ServerConfiguration config = new ServerConfiguration();
        config = service.getStringServerDes(student,config);
        stringPort = config.getStringServerPort();
        Code = config.getCode();
        config = service.getNumericServerDes(student,config);
        numericPort = config.getNumericServerPort();
        //Object Server Connect
        Socket socket;
        socket = new Socket("localhost",11050);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        oos.writeObject(student);
        Answer ans = (Answer) ois.readObject();
        socket.close();
        //String Server Connect
        socket = new Socket("localhost",stringPort);
        ois = new ObjectInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        dos.writeUTF(student.getMaSV());
        dos.writeUTF(student.getHovaten());
        dos.writeInt(student.getGroup());
        int made = dis.readInt();
        if(made == 0){
            String str1 = dis.readUTF();
            dos.writeChar(countCharacter(str1));
            String str2 = dis.readUTF();
            int n = dis.readInt();
            dos.writeUTF(Ceasar(str2,n));
        }
        if(made == 1){
            String str2 = dis.readUTF();
            int n = dis.readInt();
            dos.writeUTF(Ceasar(str2,n));
            String str1 = dis.readUTF();
            dos.writeChar(countCharacter(str1));
        }
        ans = (Answer) ois.readObject();
        dos.writeUTF("OK");
        socket.close();
        //Numeric Server Connect
        socket = new Socket("localhost",numericPort);
        ois = new ObjectInputStream(socket.getInputStream());
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        dos.writeUTF(student.getMaSV());
        dos.writeUTF(student.getHovaten());
        dos.writeInt(student.getGroup());
        dos.writeInt(made);
        if(made == 0){
            String str1 = dis.readUTF();
            String str2 = dis.readUTF();
            dos.writeBoolean(coopPrime(str1,str2));
            int a = dis.readInt();
            String b = dis.readUTF();
            System.out.println(b);
            dos.writeInt(USCLN(a,Integer.parseInt(b)));
        }
        if(made == 1){
            int a = dis.readInt();
            String b = dis.readUTF();
            System.out.println(b);
            dos.writeInt(USCLN(a,ConvertoInt(b)));
            String str1 = dis.readUTF();
            String str2 = dis.readUTF();
            dos.writeBoolean(coopPrime(str1,str2));
        }
        ois.readObject();
        dos.writeUTF("OK");
    }

    static int ConvertoInt(String str){

        String temp = str;
        String[] Chuso=new String[]{"Khong","Mot","Hai","Bay","Bon","Nam","Sau","Ba","Tam","Chin"};
        String[] so = new String[]{"0","1","2","7","4","5","6","3","8","9"};
        for(int i=0;i< Chuso.length;i++) {
            temp = temp.replaceAll(Chuso[i],so[i]);
        }
        return Integer.parseInt(temp);
    }
    static boolean coopPrime(String str1,String str2){
        int a = ConvertoInt(str1);
        int b = ConvertoInt(str2);
        if(USCLN(a,b)==1) return  true;
        else return false;
    }

    static int USCLN(int a,int b){
        int USCLN = 1;
        for(int i=2;i<a;i++){
            if(a%i==0&&b%i==0){
                USCLN = i;
            }
        }
        return USCLN;
    }

    static String Ceasar (String str, int n){
        String result="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                result+=str.charAt(i);
                continue;
            }
            result+=(char) (str.charAt(i)+n);
        }
        return result;
    }
    static char countCharacter(String str){
        int[] count =new int[256];
        for(int i=0;i<str.length();i++){
            count[str.charAt(i)]++;
        }
        int first=0;
        int second=0;
        for(int i=0;i<count.length;i++){
            if(count[i]>count[first]){
                second = first ;
                first = i ;
            }
            else if (count[i]>count[second]&&count[i]<count[first]){
                second = i;
            }
        }
        return (char) second;
    }
}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    private static ServerSocket server;
    private static int port= 80;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
            server = new ServerSocket(port);
            System.out.println("Server Created");
            while(true){
                try{
                Socket sock = server.accept();
                Scanner sc = new Scanner(sock.getInputStream());
                String type = sc.next();
                String request = sc.next();
                if(request.endsWith("/"))
                    request = new String(request+"index.html");
                request = new String(System.getProperty("user.dir")+"\\htdocs"+request);
                request = new String(request.replace("/","\\"));
                System.out.println(request);
                FileReader file = new FileReader(request);
                String resp = "";
                int i;   
                while((i=file.read())!=-1)    
                    resp = new String(resp+(char)i);
                resp = new String("HTTP/1.1 200 OK\r\n\r\n"+resp);
                sock.getOutputStream().write(resp.getBytes("UTF-8"));
                file.close();    
                sc.close();
                sock.close();}
                catch(Exception e){
                    System.out.println("Errored: "+e);
                }
                //if(request.equals("EXIT"))
                    //return;
            }
            //server.close();
    }
}
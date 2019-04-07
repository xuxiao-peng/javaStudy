package tcp.demo;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket();

            //链接超时时间
            socket.setSoTimeout(3000);


            socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000));


        System.out.println("已发送服务器链接，并进入后续流程");
        System.out.println("客户端信息:"+ socket.getLocalAddress()+" P:" +socket.getLocalPort());
        System.out.println("客户端信息:"+ socket.getInetAddress()+" P:" +socket.getPort());
        try {
            // 发送消息数据
            todo(socket);
        }catch (Exception e){
            System.out.println(e.getMessage()+ " 异常关闭");
        }
        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(Socket client) throws IOException{
        // 构建键盘出入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到Socket 输出流，转换为打印流, 向服务器的输出流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);
        // 得到socket 输入流，并转换为 BufferedReader，向自己这传输的输入流
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean flag = true;
        do {
            String str = input.readLine();

            socketPrintStream.println(str);
//            System.out.println("socketPrintStream "+str);
            String echo = socketBufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            }else {
                System.out.println(echo);  //接受回送的消息
            }
        }while (flag);
        socketBufferedReader.close();
        socketPrintStream.close();

    }
}

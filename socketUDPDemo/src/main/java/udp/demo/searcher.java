package udp.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class searcher {

    public static void main(String[] args) throws IOException {
        System.out.println("UDPProvider Started");

        // 作为搜索方，我们不指定一个端口，由系统分配
        DatagramSocket ds = new DatagramSocket();


        // 构建一份请求数据
        String requestData = "helloWord!";
         byte[] requestDataBytes = requestData.getBytes();

         // 直接构建 packet
        DatagramPacket requestPacket = new DatagramPacket(requestDataBytes,requestDataBytes.length);

        // 本地2000端口
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20001);

        // 发送
        ds.send(requestPacket);


        //构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf,buf.length);
        //接收
        ds.receive(receivePack);

        // 打印接收到的信息与发送者的信息

        // 发送者的IP地址
        String ip  = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int DataLen = receivePack.getLength();
        String data = new String(receivePack.getData(),0,DataLen);
        System.out.println("UDPSearcher receive form ip:" + ip +
                " \tport :"+port+"\tdata:"+data);

        // 构建一份回送数据

        String responseData = "Receive data with len:"+DataLen;
        byte[] responseDataBytes = responseData.getBytes();
        // 直接根据发送者构建一份回送信息

        DatagramPacket responsePacket = new DatagramPacket(responseDataBytes,
                responseDataBytes.length,
                receivePack.getAddress(),
                receivePack.getPort());
        ds.send(responsePacket);

        System.out.println("UDPSearcher Finished");
        ds.close();
    }
}

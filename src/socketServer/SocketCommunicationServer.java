package socketServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketCommunicationServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			boolean flag = true;// 设置标志位为真
			Socket client = null;// 创建Socket client以接收来自客户端的请求
			String inputLine;
			/*
			 * 1 首先调用ServerSocket类以某个端口号为参数， 创建一个ServerSocket对象，
			 * 即是服务器端的服务程序在该指定端口监听的Socket。
			 */
			ServerSocket serverSocket = new ServerSocket(1050);// 以端口9000创建一个服务器Socket
			System.out.println("服务器在端口1050上监听");
			/*
			 * 2 服务器端程序使用ServerSocket对象的accept()方法， 接收来自客户机程序的连接请求，
			 * 此时服务器端将一直保持停滞状态， 直到收到客户端发来的连接请求， 此时该方法将返回一个新建的Socket类的实例，
			 * 代表和客户机建立的通讯链路在服务程序内的通讯端点。 如果采用Java的多线程编程方法， 可以实现并发服务器，
			 * 继续监听来自其他客户的连接请求
			 */
			while (flag) {
				client = serverSocket.accept();
				/* 3 使用新建的Socket对象创建输入、输出流对象。 */
				DataInputStream input = new DataInputStream(new BufferedInputStream(client.getInputStream()));
				PrintStream output = new PrintStream(new BufferedOutputStream(client.getOutputStream()));
				/* 4 使用流对象的方法完成和客户端的数据传输，按约定协议识别并处理来自客户端的请求数据，并把处理的结果返回给客户端。 */
				while ((inputLine = input.readLine()) != null) {
					if (inputLine.equals("0")) {
						flag = false;
						System.out.println("服务器停止工作！");
						break;
					}
					System.out.println(inputLine);
					output.println(inputLine);
					output.flush();
				}
				/* 5 客户端工作完毕后，则服务器端程序关闭和客户端通讯的流和通讯的Socket。 */
				output.close();
				input.close();
				client.close();
			}
			/* 6 在服务器程序运行结束之间，应当关闭用来监听的Socket. */
			serverSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

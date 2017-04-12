package socketClient;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketCommunicationClient {
	public static boolean is(int number) {
		boolean b = true;
		if (number > 0) {
			for (int i = 2; i < number; i++) {
				if (number % i == 0) {
					b = false;
				}
			}
		}
		return b;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			/*
			 * 1首先调用Socket类的构造函数，以服务器的指定的IP地址或指定的主机名和指定的端口号为参数，创建一个Socket流，
			 * 在创建Socket流的过程中包含了向服务器请求建立通讯连接的过程实现。
			 */
			Socket clientSocket = new Socket("localhost", 1050);// 创建一个流Socket并与主机mice上的端口9000相连接
			/*
			 * 2使用Socket的方法getInputStream()和getOutputStream()来创建输入/输出流。这样，
			 * 使用Socket类后，网络输入输出也转化为使用流对象的过程。
			 */
			System.out.println("Server has connected.....");
			// 向此Socket写入字节的一个输出流
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			PrintStream output = new PrintStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			/*
			 * 3使用输入输出流对象的相应方法读写字节流数据，因为流连接着通讯所用的Socket，Socket又是和服务器端建立连接的一个端点，
			 * 因此数据将通过连接从服务器得到或发向服务器。这时我们就可以对字节流数据按客户端和服务器之间的协议进行处理，完成双方的通讯任务。
			 */
			boolean iscontinue = true;
			while (iscontinue) {
				boolean correct = false;
				Scanner scanner = new Scanner(System.in);
				int number = scanner.nextInt();
				String response;
				if (number == 0) {
					iscontinue = false;
					output.println(number);
					output.flush();
					System.out.println("客户端停止工作！");
					break;
				}
				if (number <= 4) {
					System.out.println("输入的数不大于4");
				} else {
					if (number % 2 != 0) {
						System.err.println("输入的数不是偶数");
					} else {
						correct = true;
					}
				}
				boolean isnumber = false;
				if (correct) {
					for (int i = 1; i < number; i++) {
						if (is(i) && is(number - i)) {
							isnumber = true;
						}
					}
				}
				if (isnumber) {
					output.println(number + " is successed");
				} else {
					output.println(number + " is fail");
				}
				output.flush();
				response = input.readLine();
				System.out.println("输入的数是:" + response);
			}

			/*
			 * 待通讯任务完毕后，我们用流对象的close()方法来关闭用于网络通讯的输入输出流,在用Socket对象的close()
			 * 方法来关闭Socket。
			 */
			output.close();
			input.close();
			clientSocket.close();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
		}
	}

}

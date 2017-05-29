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
			boolean flag = true;// ���ñ�־λΪ��
			Socket client = null;// ����Socket client�Խ������Կͻ��˵�����
			String inputLine;
			/*
			 * 1 ���ȵ���ServerSocket����ĳ���˿ں�Ϊ������ ����һ��ServerSocket����
			 * ���Ƿ������˵ķ�������ڸ�ָ���˿ڼ�����Socket��
			 */
			ServerSocket serverSocket = new ServerSocket(1050);// �Զ˿�9000����һ��������Socket
			System.out.println("�������ڶ˿�1050�ϼ���");
			/*
			 * 2 �������˳���ʹ��ServerSocket�����accept()������ �������Կͻ����������������
			 * ��ʱ�������˽�һֱ����ͣ��״̬�� ֱ���յ��ͻ��˷������������� ��ʱ�÷���������һ���½���Socket���ʵ����
			 * ����Ϳͻ���������ͨѶ��·�ڷ�������ڵ�ͨѶ�˵㡣 �������Java�Ķ��̱߳�̷����� ����ʵ�ֲ�����������
			 * �����������������ͻ�����������
			 */
			while (flag) {
				client = serverSocket.accept();
				/* 3 ʹ���½���Socket���󴴽����롢��������� */
				DataInputStream input = new DataInputStream(new BufferedInputStream(client.getInputStream()));
				PrintStream output = new PrintStream(new BufferedOutputStream(client.getOutputStream()));
				/* 4 ʹ��������ķ�����ɺͿͻ��˵����ݴ��䣬��Լ��Э��ʶ�𲢴������Կͻ��˵��������ݣ����Ѵ���Ľ�����ظ��ͻ��ˡ� */
				while ((inputLine = input.readLine()) != null) {
					if (inputLine.equals("0")) {
						flag = false;
						System.out.println("������ֹͣ������");
						break;
					}
					System.out.println(inputLine);
					output.println(inputLine);
					output.flush();
				}
				/* 5 �ͻ��˹�����Ϻ���������˳���رպͿͻ���ͨѶ������ͨѶ��Socket�� */
				output.close();
				input.close();
				client.close();
			}
			/* 6 �ڷ������������н���֮�䣬Ӧ���ر�����������Socket. */
			serverSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

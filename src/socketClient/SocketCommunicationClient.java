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
			 * 1���ȵ���Socket��Ĺ��캯�����Է�������ָ����IP��ַ��ָ������������ָ���Ķ˿ں�Ϊ����������һ��Socket����
			 * �ڴ���Socket���Ĺ����а������������������ͨѶ���ӵĹ���ʵ�֡�
			 */
			Socket clientSocket = new Socket("localhost", 1050);// ����һ����Socket��������mice�ϵĶ˿�9000������
			/*
			 * 2ʹ��Socket�ķ���getInputStream()��getOutputStream()����������/�������������
			 * ʹ��Socket��������������Ҳת��Ϊʹ��������Ĺ��̡�
			 */
			System.out.println("Server has connected.....");
			// ���Socketд���ֽڵ�һ�������
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			PrintStream output = new PrintStream(new BufferedOutputStream(clientSocket.getOutputStream()));
			/*
			 * 3ʹ������������������Ӧ������д�ֽ������ݣ���Ϊ��������ͨѶ���õ�Socket��Socket���Ǻͷ������˽������ӵ�һ���˵㣬
			 * ������ݽ�ͨ�����Ӵӷ������õ��������������ʱ���ǾͿ��Զ��ֽ������ݰ��ͻ��˺ͷ�����֮���Э����д������˫����ͨѶ����
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
					System.out.println("�ͻ���ֹͣ������");
					break;
				}
				if (number <= 4) {
					System.out.println("�������������4");
				} else {
					if (number % 2 != 0) {
						System.err.println("�����������ż��");
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
				System.out.println("���������:" + response);
			}

			/*
			 * ��ͨѶ������Ϻ��������������close()�������ر���������ͨѶ�����������,����Socket�����close()
			 * �������ر�Socket��
			 */
			output.close();
			input.close();
			clientSocket.close();
		} catch (Exception e) {
			System.err.println("Exception :" + e);
		}
	}

}

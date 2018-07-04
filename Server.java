import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
	static ExecutorService executorService; // ������Ǯ
	static ServerSocket serverSocket;
	static List<Client> connections = new Vector<Client>();
	
	static void startServer() { // ���� ���� �� ȣ��
		// ������Ǯ ����
		executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors()
	    );
		
		// ���� ���� ���� �� ���ε�
		try {
			serverSocket = new ServerSocket();		
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
		} catch(Exception e) {
			if(!serverSocket.isClosed()) { stopServer(); }
			return;
		}
		
		// ���� �۾� ����
		Runnable runnable = new Runnable() {
			@Override
			public void run() {	
				System.out.println("[���� ����]");
				while(true) {
					try {
						// ���� ����
						Socket socket = serverSocket.accept();
						System.out.println("[���� ����: " + socket.getRemoteSocketAddress()  + ": " + Thread.currentThread().getName() + "]");		
						// Ŭ���̾�Ʈ ���� ��û �� ��ü �ϳ��� �����ؼ� ����
						Client client = new Client(socket);
						connections.add(client);
						System.out.println("[���� ����: " + connections.size() + "]");
					} catch (Exception e) {
						if(!serverSocket.isClosed()) { stopServer(); }
						break;
					}
				}
			}
		};
		// ������Ǯ���� ó��
		executorService.submit(runnable);
	}
	
	static void stopServer() { // ���� ���� �� ȣ��
		try {
			// ��� ���� �ݱ�
			Iterator<Client> iterator = connections.iterator();
			while(iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			// ���� ���� �ݱ�
			if(serverSocket!=null && !serverSocket.isClosed()) { 
				serverSocket.close(); 
			}
			// ������Ǯ ����
			if(executorService!=null && !executorService.isShutdown()) { 
				executorService.shutdown(); 
			}
			System.out.println("[���� ����]");
		} catch (Exception e) { }
	}	
	
	static class Client {
		Socket socket;
		
		Client(Socket socket) {
			this.socket = socket;
			receive();
		}
		
		void receive() {
			// �ޱ� �۾� ����
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while(true) {
							byte[] byteArr = new byte[100];
							InputStream inputStream = socket.getInputStream();
							
							// ������ read
							int readByteCount = inputStream.read(byteArr);
							
							// Ŭ���̾�Ʈ�� ���������� Socket�� close()�� ȣ������ ���
							if(readByteCount == -1) {  throw new IOException(); }
							
							System.out.println("[��û ó��: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]");
							
							// ���ڿ��� ��ȯ
							String data = new String(byteArr, 0, readByteCount, "UTF-8");
							
							// Ŭ���̾�Ʈ�� stop server��� �������� ���� ����
							if(data.equals("stop server")) 
							{
								stopServer();
							}
							
							// ��� Ŭ���̾�Ʈ���� ������ ����
							for(Client client : connections) {
								client.send(data); 
							}
						}
					} catch(Exception e) {
						try {
							connections.remove(Client.this);
							System.out.println("[Ŭ���̾�Ʈ ��� �ȵ�: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]");
							socket.close();
						} catch (IOException e2) {}
					}
				}
			};
			// ������Ǯ���� ó��
			executorService.submit(runnable);
		}
	
		void send(String data) {
			// ������ �۾� ����
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						// Ŭ���̾�Ʈ�� ������ ������
						byte[] byteArr = data.getBytes("UTF-8");
						OutputStream outputStream = socket.getOutputStream();
						// ������ write
						outputStream.write(byteArr);
						outputStream.flush();
					} catch(Exception e) {
						try {
							System.out.println("[Ŭ���̾�Ʈ ��� �ȵ�: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName() + "]");
							connections.remove(Client.this);
							socket.close();
						} catch (IOException e2) {}
					}
				}
			};
			// ������Ǯ���� ó��
			executorService.submit(runnable);
		}
	}
	
	public static void main(String[] args) {
		startServer();
	}
}



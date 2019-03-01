package day17;

import java.io.*;
import java.net.*;

public class WebServer {
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(8888);
			while (true) {
				socket = ss.accept();
				RequestProcess rp = new RequestProcess(socket);
				rp.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class RequestProcess extends Thread {
	private Socket socket;

	public RequestProcess(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		PrintWriter pw = null;
		InputStream is = null;
		try {
			pw = new PrintWriter(socket.getOutputStream(), true);
			is = socket.getInputStream();

			String adr = socket.getRemoteSocketAddress().toString();
			pw.println("<html><body>");
			pw.println("<h3>Client Information</h3><hr/>");
			pw.println("<table border=\"1\">");
			pw.println("<tr><th>Socket Address</th>");
			pw.println("<th>Port</th></tr>");
			pw.println("<tr><td>" + adr);
			pw.println("</td><td>" + socket.getPort() + "</td></tr>");
			pw.println("</table></body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (pw != null)
					pw.close();
				if (socket != null)
					socket.close();
			} catch (Exception e) {
			}
		}
	}
}

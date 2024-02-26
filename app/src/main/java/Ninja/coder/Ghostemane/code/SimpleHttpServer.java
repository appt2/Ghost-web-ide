package Ninja.coder.Ghostemane.code;

import android.net.Uri;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import android.util.Log;

public class SimpleHttpServer {
	public WebServer server;

	private String indexFile;

	public SimpleHttpServer(int port, String indexFile) {

		this.indexFile = indexFile;
	}

	public void startServer() {
		try {
			server = new WebServer();
			server.start();
		} catch (IOException e) {
		}
	}

	public void stopServer() {
		if (server != null) {
			server.stop();
		}
	}

	public String getLocalIpAddress() {
		return "http://localhost:8080";
	}

	private class WebServer extends NanoHTTPD {

		public WebServer() {
			super("localhost", 8080);
		}

		@Override
		public Response serve(IHTTPSession session) {
			String uri = session.getUri();
			if (uri.endsWith("/")) {
				uri += indexFile;
			}
			String filePath = uri;

			try {
				
				FileInputStream fis = new FileInputStream(filePath);
				String mimeType = getMimeTypeForFile(filePath);
				return newChunkedResponse(Response.Status.OK, mimeType, fis);
			} catch (IOException e) {
				e.printStackTrace();
				return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT,
						"Internal server error: " + e.getMessage());
			}
		}
	}

}

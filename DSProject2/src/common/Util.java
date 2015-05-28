package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Util {

	public static String fileToByteString(File file) {
		byte[] bytes = new byte[(int) file.length()];
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			bis.read(bytes);
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return new String(Base64.encodeBase64(bytes), "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static File bytesStringToFile(File file, String string) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bos.write(Base64.decodeBase64(string));
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public static void sendFile(DataOutputStream out, File file) {
		System.out.println("Send File: " + file.getPath());
		try {
			byte[] buffer = new byte[8192];
			FileInputStream in = new FileInputStream(file);
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static File receiveFile(DataInputStream in, File file) {
		System.out.println("Receive File: " + file.getPath());
		try {
			byte[] buffer = new byte[8192];
			FileOutputStream out = new FileOutputStream(file);
			int len;
			do {
				len = in.read(buffer);
				out.write(buffer, 0, len);
			} while (len == 8192);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public static void send(DataOutputStream out, String message) {
		send(out, message, null);
	}

	public static void send(DataOutputStream out, String message, String jobId) {
		try {
			String data = new Instruction(message, jobId).toJson();
			out.writeUTF(data);
			System.out.println("Send: " + data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Instruction receive(DataInputStream in) {
		try {
			String data = in.readUTF();
			// TODO deal with receive file
			if (data.length() < 1000) {
				System.out.println("Receive: " + data);
				return Instruction.fromJson(data);
			}
			else {
				return new Instruction("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static File createFile(String fileName, String folderPath){
		File file = new File(folderPath + fileName);
		try {
			file.createNewFile();
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
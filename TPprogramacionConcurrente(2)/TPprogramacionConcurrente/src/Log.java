import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.filechooser.FileSystemView;

public class Log {
	
	private File file;
	private FileWriter fichero;
	private PrintWriter pw;
	private BufferedReader br;
	private StringBuffer info;
	
	public Log() {
		info = new StringBuffer();
		fichero = null;
		pw = null;
		try {
			file = new File(
					FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
							+ "/log.txt");
			fichero = new FileWriter(file);
			pw = new PrintWriter(fichero);
			br = new BufferedReader(
					new FileReader(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
					+ "/log.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escribir(String texto) {
		pw.print(texto);
	}

	public void escribirLog(StringBuffer texto) {
		fichero = null;
		pw=null;
		File file1 = new File(
				FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
						+ "/log1.txt");
		try {
			fichero = new FileWriter(file1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		abrir();
		pw.print(texto);
		cerrar();
		/* file.delete();
		file1.renameTo(file); */
	}
	
	public String leer() {
		try {
			return br.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public void abrir() {
		try {
			pw = new PrintWriter(fichero);
			br = new BufferedReader(
					new FileReader(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
					+ "/log.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrar() {
		try {
			pw.close();
			br.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public StringBuffer sacarInfo() {
		abrir();
		String linea;
		while ((linea = leer()) != null) {
			//System.out.println(linea);
			info.append(linea);
			//System.out.println(info);
		}
		cerrar();
		return info;
	}


}


package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;

public class Backup {

	private static final SimpleDateFormat DATAHORA = new SimpleDateFormat("ddMMyyyy_HHmmss"); 
	
	private String pathAbsolutoParcial() {

		// Criando um nome qualquer de arquivo
		// Parece que por padrão, ele usa a raiz do projeto para 'salvar' onde o arquivo vai ficar
		File file = new File("arquivo.ext");

		// Buscando o caminho completo
		String pathAbsolutoAtual = file.getAbsolutePath();
		
		String pathAbsolutoParcial = null;

		// Buscando apenas o diretório onde o arquivo está
		pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('/'));
		
		System.out.println("pathAbsolutoAtual = " + pathAbsolutoAtual);
		// pathAbsolutoAtual = /home/tomaz/eclipse-workspace/YTBSwingJasperClientes/arquivo.ext
		
		System.out.println("pathAbsolutoParcial = " + pathAbsolutoParcial);
		// pathAbsolutoParcial = /home/tomaz/eclipse-workspace/YTBSwingJasperClientes
		
		return pathAbsolutoParcial;
	}

	// Listar todos os arquivos de backups
	public ArrayList<String> listarArquivos(){
		
		// Buscando o caminho do diretório de arquivos
		String pathDiretorio = pathAbsolutoParcial();
		
		File diretorio = new File(pathDiretorio);
		
		ArrayList<String> arquivosBackups = new ArrayList<>();
		
		// Verificar se tem algum arquivo no diretório
		if(diretorio.exists()) {
			File[] arquivosDeiretorio = diretorio.listFiles();
			
			if (arquivosDeiretorio != null && arquivosDeiretorio.length > 0) {
				for (File arquivo : arquivosDeiretorio) {
					if (arquivo.isFile()) {
						if (arquivo.getName().contains("backup")) {
							arquivosBackups.add(arquivo.getAbsolutePath());
						}
					}
				}
			}
		}
		
		return arquivosBackups;
	}
	
	public void gerarBackup() {
		
		StringBuilder pathDiretorio = new StringBuilder(pathAbsolutoParcial());
		
		StringBuilder zipPath = new StringBuilder();
		
		pathDiretorio.append("/");
		
		zipPath.append(pathDiretorio);
		zipPath.append("backup");
		zipPath.append(DATAHORA.format(new Date()));
		zipPath.append(".zip");
		
		FileOutputStream fos = null;
		ZipOutputStream zip = null;
		
		try {
			fos = new FileOutputStream(zipPath.toString());
			zip = new ZipOutputStream(fos);
			
			pathDiretorio.append("resources");
			
			adicionarAoZip("", pathDiretorio.toString(), zip);
			
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {				
				// Essa ordem é importante.
				zip.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		
		JOptionPane.showMessageDialog(null, "Backup gerado com sucesso!");
	}
	
	public void adicionarAoZip(String caminhoZip, String diretorioPath, ZipOutputStream zip) throws IOException {
		
		File diretorio = new File(diretorioPath);
		
		for (String nomeArquivo : diretorio.list()) {			
			
			String caminhoCompletoArquivo = diretorioPath + "/" + nomeArquivo;
			
			if(new File(caminhoCompletoArquivo).isDirectory()) {				
				
				adicionarAoZip(caminhoZip + nomeArquivo + "/", caminhoCompletoArquivo, zip);				
				continue;
			}
			
			ZipEntry zipEntry = new ZipEntry(caminhoZip + nomeArquivo);
			
			zip.putNextEntry(zipEntry);
			
			FileInputStream fileInputStream = new FileInputStream(caminhoCompletoArquivo);
			
			byte[] buffer = new byte[1024];
			
			int i;
			while ((i = fileInputStream.read(buffer)) > 0) {
				zip.write(buffer, 0, i);
			}
			
			fileInputStream.close();
		}
	}
	
	public void restaurarBackup(String caminhoArquivoZip) throws FileNotFoundException, IOException {
		
		byte[] buffer = new byte[1024];
		
		try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(caminhoArquivoZip))) {
			
			ZipEntry entry;
			
			while((entry = zipInputStream.getNextEntry()) != null) {
				
				String nomeArquivo = entry.getName();
				File arquivo = new File(pathAbsolutoParcial() + "/resources" + File.separator + nomeArquivo);
				
				if(entry.isDirectory()) {
					arquivo.mkdirs();
					continue;
				}
				
				File parent = arquivo.getParentFile();
				
				if(!parent.exists()) {
					parent.mkdirs();
				}
				
				try(FileOutputStream fileOutputStream = new FileOutputStream(arquivo)){
					int i;
					while((i = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, i);
					}
				}
			}
			
			JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso!");
		}
	}
	
}

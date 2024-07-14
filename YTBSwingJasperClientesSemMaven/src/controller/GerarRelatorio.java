package controller;

import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GerarRelatorio {
	
	private String modeloJrxml = "relatorio/Coffee.jrxml";
	
	public GerarRelatorio() {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		//JasperRepo
		// /home/tomaz/eclipse-workspace/ClientesSwing/relatorio/Coffee.jrxml
		
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("/home/tomaz/eclipse-workspace/YTBSwingJasperClientes/relatorio/Coffee.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(modeloJrxml);
						
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);

			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
				// false para não fechar o sistema todo, apenas o relatório.		
			
			Conexao.getInstancia().fecharConexao();
				// Precisa fechar a conexão depois de gerar o relatório, senão não consiguirei incuir/alterar/etc novos registros.				
			
			jasperViewer.setVisible(true);
			
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
}

//Olá amigo. Seu tutorial é excelente, mas estou tendo um problema para gerar o relatório. Quando clico no botão "Gerar relatório" gera o seguinte erro: 
//    "Exception in thread "AWT-EventQueue-0" java.lang.NoClassDefFoundError: org/springframework/beans/factory/support/BeanDefinitionRegistry"
//Acredito que deve ser a versão dos JARs. Estou testando com o "jasperreports-6.21.3.jar". Gostaria de saber qual é a versão

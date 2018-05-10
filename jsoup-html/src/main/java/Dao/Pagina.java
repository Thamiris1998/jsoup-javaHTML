package Dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Pagina {
	public static void main(String[] args) {
		// OBS: n�o h� necessidade da cria��o da data s� foi feito para
		// padronizar o nome dos arquivos criados
		// O calendar � instanciado para se obter a data atual
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();

		// A data atual e formatada para o formato que melhor atender suas
		// necessidades
		SimpleDateFormat sdf = new SimpleDateFormat("_dd_MM_yyyy");

		// Ap�s formatada a data a mesma � concatenada com o nome do arquivo
		File file = new File("C:/Users/tlopes/Downloads/print" + sdf.format(data) + ".html");
		
		//� criada o fluxo de sa�da para gravar dados 
		try (FileOutputStream fop = new FileOutputStream(file)) {
			
			//� criada conex�o com jsoup para obtermos as informa��es do site
			Document doc = Jsoup.connect("http://bafafa.com.br/arte-e-cultura/festa").get();

			// Caso n�o exista um arquivo com a nomenclatura declarada � criado um novo
			if (!file.exists()) {
				file.createNewFile();
			}
			
			//Pegarmos o valor do Document e convertemos para string e ap�s para byte
			//Para que as informa��es do site possam ser salva em html
			byte[] contentInBytes = doc.toString().getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Salvo");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

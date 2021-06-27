import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;


public class Regex {
	private Log log;
	private StringBuffer invariantes = new StringBuffer();
	

	// No separar en grupos los invariantes

	private String expresionRegular = "((-T16-)(.*?)(((-T0-)(.*?)(-T1-)(.*?)(((-T4-)(.*?)(-T5-)(.*?)(-T2-)(.*?)(-T3-)(.*?)(-T7-))|((-T2-)(.*?)(-T3-)(.*?)(-T6-))|((-T2-)(.*?)(-T6-)(.*?)(-T3-))|((-T6-)(.*?)(-T2-)(.*?)(-T3-))))|((-T8-)(.*?)(-T9-)(.*?)(((-T12-)(.*?)(-T13-)(.*?)(-T10-)(.*?)(-T11-)(.*?)(-T15-))|((-T10-)(.*?)(-T11-)(.*?)(-T14-))|((-T10-)(.*?)(-T14-)(.*?)(-T11-))|((-T14-)(.*?)(-T10-)(.*?)(-T11-))))))";

	List<Integer> startIndexs = new ArrayList<>();
	List<Integer> endIndexs = new ArrayList<>();
	List<InvTransicion> listaInvariantes = new ArrayList<>();

	private int[] contadores = {0, 0, 0, 0, 0, 0, 0, 0};
	private int contadorTodo = 0;

	public Regex(Log log, List<InvTransicion> listaInvariantes) {
		this.log = log;
		this.listaInvariantes = listaInvariantes;
	}

	public void chequeoInvariantes() {
		invariantes = log.sacarInfo();
		//invariantes.append("16  0  1  4  5  7  2  3  16    0  1    2   3  6 10  11  8  9  12  13  4  5   15  7  1  8  9  12  13  4  5   10   11  15    10  11   8  9    12  13  6  15     1  8  9  6  12   13   10  11    10    11     15  1  8  9   6   12  13 "); 
		Pattern patternTodo = Pattern.compile(expresionRegular);

		
		int antes = 0;
		int despues = 0;
		boolean seguir = true;

		while (seguir) {

			antes = contadorTodo;

			Matcher matcherExpresionRegular = patternTodo.matcher(invariantes);

			while (matcherExpresionRegular.find()) { //mientras que la expresion regular haga match
				int invarianteMatcheado = invarianteMatch(matcherExpresionRegular);
				contadores[invarianteMatcheado] = contadores[invarianteMatcheado] + 1;
				LlenarListaInvariantes(matcherExpresionRegular, listaInvariantes.get(invarianteMatcheado));
				contadorTodo++;
				
			}
			QuitarListaInvariantes();
			

			despues = contadorTodo;

			if (antes == despues) {
				
				seguir = false;
			}

		}

		//imprimir contadores
		for (int i = 0; i < contadores.length; i++) {
			System.out.println("invariante " + i + ": " + contadores[i]);
		}
		String resultado = invariantes.toString();
		System.out.println(resultado.trim());
		log.escribirLog(invariantes);
	}

	private int invarianteMatch (Matcher matcherExpresionRegular) {//me fijo que invariante matcheo y lo devuelvo
		int inv = 0;
		for (int i = 0; i < listaInvariantes.size(); i++) {
			int contador = 0;
			
			int [] grupos = listaInvariantes.get(i).getGrupos();
			for (int j = 0; j < grupos.length; j++) {
				if (matcherExpresionRegular.group(grupos[j]) != null) {
					
					contador++;
				}
			}
			if (contador==grupos.length) {
				inv = i;
				break;
			}
		}
		
		//System.out.println("invariante encontrado es : " + inv);
		return inv;
	}

	private void QuitarListaInvariantes () { //elimina los invariantes ya encontrados con los indices de la lista
		for (int j = startIndexs.size(); j > 0; j--) {
			invariantes.delete(startIndexs.get(j - 1), endIndexs.get(j - 1));
		}
		startIndexs.clear();
		endIndexs.clear();
	}

	private void LlenarListaInvariantes (Matcher matcherExpresionRegular, InvTransicion invariante) { //agrega los indices de los invariantes encontrados a la lista
		int[] grupos = invariante.getGrupos();
		for (int i = 0; i < grupos.length; i++) {
			startIndexs.add(matcherExpresionRegular.start(grupos[i]));
			endIndexs.add(matcherExpresionRegular.end(grupos[i]));
			//System.out.println("grupos para sacar " + grupos[i]);
		}
	}

}

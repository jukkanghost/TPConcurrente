import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;


public class Regex {
	private Log log;
	private StringBuffer invariantes = new StringBuffer();
	

	// No separar en grupos los invariantes

	private String expresionRegular = "((-T16-)(.*?)(((-T0-)(.*?)(-T1-)(.*?)(((-T4-)(.*?)(-T5-)(.*?)(-T2-)(.*?)(-T3-)(.*?)(-T7-))|((-T6-)(.*?)(-T2-)(.*?)(-T3-))|((-T2-)(.*?)(-T6-)(.*?)(-T3-))|((-T2-)(.*?)(-T3-)(.*?)(-T6-))))|((-T8-)(.*?)(-T9-)(.*?)(((-T12-)(.*?)(-T13-)(.*?)(-T10-)(.*?)(-T11-)(.*?)(-T15-))|((-T14-)(.*?)(-T10-)(.*?)(-T11-))|((-T10-)(.*?)(-T14-)(.*?)(-T11-))|((-T10-)(.*?)(-T11-)(.*?)(-T14-))))))";

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
		Pattern patternTodo = Pattern.compile(expresionRegular);

		
		int antes = 0;
		int despues = 0;
		boolean seguir = true;

		while (seguir) {

			antes = contadorTodo;

			Matcher matcherExpresionRegular = patternTodo.matcher(invariantes);
			//System.out.println(invariantes.toString());
			if (matcherExpresionRegular.find()) { //mientras que la expresion regular haga match
			//	System.out.println("matcheo: " + matcherExpresionRegular.toMatchResult().group());
				int invarianteMatcheado = invarianteMatch(matcherExpresionRegular);
				contadores[invarianteMatcheado] = contadores[invarianteMatcheado] + 1;
				LlenarListaInvariantes(matcherExpresionRegular, listaInvariantes.get(invarianteMatcheado));
				contadorTodo++;
			}
			QuitarListaInvariantes();
			//System.out.println(invariantes.toString());
			

			despues = contadorTodo;

			if (antes == despues) {
				
				seguir = false;
			}

		}

		//imprimir contadores
		int serv1 = 0, serv2 = 0;
		for (int i = 0; i < contadores.length; i++) {
			System.out.println("invariante " + i + ": " + contadores[i]);
		}
		for (int i = 0; i < 4; i++) {
			serv1 += contadores[i]; 
		}
		for (int i = 4; i < 8; i++) {
			serv2 += contadores[i]; 
		}
		System.out.println("Invariantes encontrados en total " + contadorTodo);
		System.out.println("Servicio 1 realizo " + serv1 + " tareas.\nServicio 2 realizo " + serv2 + " tareas.");
		String resultado = invariantes.toString();
		if (resultado.length() == 0) {
			System.out.println("Ejecucion Correcta");
		}
		else{
			System.out.println(resultado.trim());
			
		}
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
		//	System.out.println("grupos para sacar " + grupos[i]);
		}
	}

}

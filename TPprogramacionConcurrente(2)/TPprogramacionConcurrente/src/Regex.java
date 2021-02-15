import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Regex {
	private Log log;
	private StringBuffer invariantes = new StringBuffer();
	

	// No separar en grupos los invariantes

	private String expresionRegular = "(16)(.*?)(((\\s0)(.*?)(\\s1\\s)(.*?)(((\\s4)(.*?)(\\s5)(.*?)(7)(.*?)(\\s2)(.*?)(\\s3))|((\\s2)(.*?)(\\s3))(.*?)(6)))|((8)(.*?)(9)(.*?)(((12)(.*?)(13)(.*?)(15)(.*?)(10)(.*?)(11))|((14)(.*?)(10)(.*?)(11)))))";

	List<Integer> startIndexs = new ArrayList<>();
	List<Integer> endIndexs = new ArrayList<>();
	private int contadorinv1 = 0;
	private int contadorinv2 = 0;
	private int contadorinv3 = 0;
	private int contadorinv4 = 0;
	private int contadorTodo = 0;

	public Regex(Log log) {
		this.log = log;
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

			while (matcherExpresionRegular.find()) {
				if (matcherExpresionRegular.group(1) != null) {
					LlenarListaInvariantes(matcherExpresionRegular,1);
					if (matcherExpresionRegular.group(4) != null) {
						LlenarListaInvariantes(matcherExpresionRegular, 5);
						LlenarListaInvariantes(matcherExpresionRegular, 7);
						if (matcherExpresionRegular.group(10) != null) {
							contadorinv1++;
							LlenarListaInvariantes(matcherExpresionRegular, 11);
							LlenarListaInvariantes(matcherExpresionRegular, 13);
							LlenarListaInvariantes(matcherExpresionRegular, 15);
							LlenarListaInvariantes(matcherExpresionRegular, 17);
							LlenarListaInvariantes(matcherExpresionRegular, 19);
						} else if (matcherExpresionRegular.group(20) != null) {
							contadorinv2++;
							LlenarListaInvariantes(matcherExpresionRegular, 21);
							LlenarListaInvariantes(matcherExpresionRegular, 23);
							LlenarListaInvariantes(matcherExpresionRegular, 25);
						}
					} else if (matcherExpresionRegular.group(26) != null) {
						LlenarListaInvariantes(matcherExpresionRegular, 27);
						LlenarListaInvariantes(matcherExpresionRegular, 29);
						if (matcherExpresionRegular.group(32) != null) {
							contadorinv3++;
							LlenarListaInvariantes(matcherExpresionRegular, 33);
							LlenarListaInvariantes(matcherExpresionRegular, 35);
							LlenarListaInvariantes(matcherExpresionRegular, 37);
							LlenarListaInvariantes(matcherExpresionRegular, 39);
							LlenarListaInvariantes(matcherExpresionRegular, 41);
						} else if (matcherExpresionRegular.group(42) != null) {
							contadorinv4++;
							LlenarListaInvariantes(matcherExpresionRegular, 43);
							LlenarListaInvariantes(matcherExpresionRegular, 45);
							LlenarListaInvariantes(matcherExpresionRegular, 47);
						}
					}
					contadorTodo++;
				}
			}
			
			QuitarListaInvariantes();
			
			despues = contadorTodo;

			if (antes == despues) {
				seguir = false;
			}

		}

		System.out.println("invariante 1 " + contadorinv1);
		System.out.println("invariante 2 " + contadorinv2);
		System.out.println("invariante 3 " + contadorinv3);
		System.out.println("invariante 4 " + contadorinv4);

		log.escribirLog(invariantes);
	}

	private void QuitarListaInvariantes () {
		for (int j = startIndexs.size(); j > 0; j--) {
			invariantes.delete(startIndexs.get(j - 1), endIndexs.get(j - 1));
		}
		startIndexs.clear();
		endIndexs.clear();
	}

	private void LlenarListaInvariantes (Matcher matcherExpresionRegular, int group) {
		startIndexs.add(matcherExpresionRegular.start(group));
		endIndexs.add(matcherExpresionRegular.end(group));
	}

}

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Regex {
	private Log log;
	private StringBuffer texto = new StringBuffer();

	// No separar en grupos los invariantes

	private String invarianteTodo = "(16)(.*?)(((\\s0)(.*?)(\\s1\\s)(.*?)(((\\s4)(.*?)(\\s5)(.*?)(7)(.*?)(\\s2)(.*?)(\\s3))|((6)(.*?)(\\s2)(.*?)(\\s3))))|((8)(.*?)(9)(.*?)(((12)(.*?)(13)(.*?)(15)(.*?)(10)(.*?)(11))|((14)(.*?)(10)(.*?)(11)))))";

	List<Integer> startIndexs = new ArrayList<>();
	List<Integer> endIndexs = new ArrayList<>();

	public Regex(Log log) {
		this.log = log;
	}

	public void chequeoInvariantes() {
		texto = log.sacarInfo();
		

		Pattern patternTodo = Pattern.compile(invarianteTodo);

		int contadorinv1 = 0;
		int contadorinv2 = 0;
		int contadorinv3 = 0;
		int contadorinv4 = 0;
		int contadorTodo = 0;
		int antes = 0;
		int despues = 0;
		boolean seguir = true;

		while (seguir) {

			antes = contadorTodo;

			Matcher matcherTodo = patternTodo.matcher(texto);

			while (matcherTodo.find()) {
				if (matcherTodo.group(1) != null) {
					startIndexs.add(matcherTodo.start(1));
					endIndexs.add(matcherTodo.end(1));
					if (matcherTodo.group(5) != null) {
						startIndexs.add(matcherTodo.start(5));
						endIndexs.add(matcherTodo.end(5));
						startIndexs.add(matcherTodo.start(7));
						endIndexs.add(matcherTodo.end(7));
						if (matcherTodo.group(10) != null) {
							contadorinv1++;
							startIndexs.add(matcherTodo.start(11));
							endIndexs.add(matcherTodo.end(11));
							startIndexs.add(matcherTodo.start(13));
							endIndexs.add(matcherTodo.end(13));
							startIndexs.add(matcherTodo.start(15));
							endIndexs.add(matcherTodo.end(15));
							startIndexs.add(matcherTodo.start(17));
							endIndexs.add(matcherTodo.end(17));
							startIndexs.add(matcherTodo.start(19));
							endIndexs.add(matcherTodo.end(19));
						}
				 else if (matcherTodo.group(20) != null) {
					contadorinv2++;
					startIndexs.add(matcherTodo.start(21));
					endIndexs.add(matcherTodo.end(21));
					startIndexs.add(matcherTodo.start(23));
					endIndexs.add(matcherTodo.end(23));
					startIndexs.add(matcherTodo.start(25));
					endIndexs.add(matcherTodo.end(25));
				} 
			}
				contadorTodo++;
				}
			}
			
			for (int j = startIndexs.size(); j > 0; j--) {
				texto.delete(startIndexs.get(j - 1), endIndexs.get(j - 1));
			}
			startIndexs.clear();
			endIndexs.clear();

			despues = contadorTodo;

			if (antes == despues) {
				seguir = false;
			}

		}

		System.out.println("invariante 1 " + contadorinv1);
		System.out.println("invariante 2 " + contadorinv2);
		System.out.println("invariante 3 " + contadorinv3);
		System.out.println("invariante 4 " + contadorinv4);


		log.escribirLog(texto);
	}

}

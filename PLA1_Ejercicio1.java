import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry;


/* EJERCICIO 1 - AN�LISIS DE TEXTO
  
El usuario introducir� un texto largo por la consola. Por ejemplo:
	Hacia un buen d�a. Las mariposas revoloteaban sobre los campos en flor. Los pajaritos inundaban el aire con sus trinos. Y los mosquitos se merendaban mis brazos llen�ndolos de ronchas.
El programa almacenar� cada frase en un ArrayList. Frase es todo lo que est� separado por un punto. En este caso:

	Hacia un buen d�a. 

	Las mariposas revoloteaban sobre los campos en flor. 

	Los pajaritos inundaban el aire con sus trinos. 

	Y los mosquitos se merendaban mis brazos llen�ndolos de ronchas.

En otro ArrayList se van a almacenar todas las palabras del texto. Palabra es todo lo que est� separado por espacios. 
Ejemplo:

	Hacia
	un
	buen
	d�a

Despu�s de almacenar estos datos el programa nos mostrar� la lista de las frases ordenadas de la m�s corta a la m�s 
larga (en n�mero de letras) y las palabras en orden de las m�s frecuentes a las menos, indicando para cada palabra 
su n�mero de apariciones. Si varias palabras tienen el mismo n�mero de apariciones se utilizar� el orden alfab�tico.*/

public class PLA1_Ejercicio1 {
	 public static void main(String args[]) 
	 {
		 String aux;
		 String palabra;
		 String frase = null;
		 String ultima;
		 String aux2;
		
		 int num_frases = 0;
		 int nr_palabras_en_frase = 0;
		 int suma_letras_frase = 0;
		 int m;
	
   	// Se introduce el texto.
		 Scanner sc = new Scanner(System.in); // se crea la variable sc que permite introducir datos por teclado
		 aux = sc.nextLine();
		 sc.close();
		 ArrayList<String> frases = new ArrayList<String>(Arrays.asList(aux.split("\\. "))); // arraylist con las frases del texto separadas.
		 num_frases = frases.size();
		 
		 for (m = 0; m < num_frases; m++) {
		 	ultima = frases.get(m).replace("." , "");
		 	frases.set(m, ultima);
		 }
		
		 ArrayList<String> frases_punto = (ArrayList<String>)frases.clone();
		 for (int k = 0; k < num_frases; k++) {
			 aux2 = frases_punto.get(k).concat(".");
			 frases_punto.set(k, aux2);	
		 }
		 
		 
	// Creaci�n de arraylist con las palabras del texto.	 	
		 
		 List<String> palabras = new ArrayList<String>(); // arraylist con las palabras de todas las frase.
		 List<String> palabra_aux = new ArrayList<String>(); // arraylist temporal para guardar las palabras de una frase.
		 int long_frases[][] = new int[num_frases][2];
		 for (int i = 0; i < num_frases; i++) {
			 frase = frases.get(i).toLowerCase(); // se extrae una frase.
			 palabra_aux = (Arrays.asList(frase.split(" "))); // se guardan temporalmente las palabras de la frase actual.
			 nr_palabras_en_frase = palabra_aux.size();
			 suma_letras_frase = 0;
			 	for (int j = 0; j < nr_palabras_en_frase; j++) {
			 		palabra = palabra_aux.get(j);
			 		suma_letras_frase += palabra.length();
			 	}
			long_frases[i][0] = i;
        	long_frases[i][1] = suma_letras_frase;
        	palabras.addAll(palabra_aux); // se concatena la arraylist total con la temporal.
		 } 
		
		 // Se ordena el array con el �ndice de cada frase y el n�mero de caracteres de la misma.
		 for (int i = 0; i < (num_frases); i++){
	            for(int j = i + 1; j < num_frases; j++){
	                if(long_frases[i][1] > long_frases[j][1])
	                {
	                	int aux4 = long_frases[i][0];
	                    int aux3 = long_frases[i][1];
	                    // Guardamos �ndice ordenado.
	                    long_frases[i][0] = long_frases[j][0];
	                    long_frases[j][0] = aux4;
	                    // Guardamos n�mero de caracteres de las frases ordenado.
	                    long_frases[i][1] = long_frases[j][1];
	                    long_frases[j][1] = aux3;
	                   }
	            }
		 }
		 
		 // Se cuenta el n�mero de apariciones de cada palabra. 
		 List<Integer> pos_pal_repetidas = new ArrayList<>(); // almacena el �ndice de la palabra repetida del arraylist palabras.
		 List<Integer> repeticiones_palabra = new ArrayList<>(); // almacena las veces que se repite la palabra.
		 List<String> pal_repetidas = new ArrayList<>();
		 int repeticiones = 0;

		 for (int i = 0; i < palabras.size(); i++) {
			 for (int j = i+1; j < palabras.size(); j++) {
				 if (palabras.get(i).equals(palabras.get(j))) {
					 pos_pal_repetidas.add(i);
					 break;
				 }
			 }
		 }
		
		 for(m = 0; m < pos_pal_repetidas.size(); m++) {
	            pal_repetidas.add(palabras.get(pos_pal_repetidas.get(m)));
		 }
	//	 List<String> pal_repetidas_sin_duplicar = pal_repetidas.stream().distinct().collect(Collectors.toList());
		 
		 	repeticiones_palabra.add(repeticiones);
		 	
		 	int ind = 0;
		 	String aux_repeticiones[][];
		
		 	// Se obtiene el arraylist de todas las palabras sin que se repita ninguna.
		 	LinkedHashSet<String> hashSet = new LinkedHashSet<>(palabras);
	        ArrayList<String> palabras_sin_duplicar = new ArrayList<>(hashSet);
	        
		 		Map<String, Long> frequencyMap = palabras.stream().collect(Collectors.groupingBy(Function.identity(), 
															Collectors.counting()));

		 		
		 		// Se extrae la lista desordenada con las diferentes palabras y el n�mero de apariciones de cada una.
	
		 		aux_repeticiones = new String[palabras_sin_duplicar.size()][2];
		 		ind = 0;
		 		for (Map.Entry<String, Long> entry : frequencyMap.entrySet()) {
		 				aux_repeticiones[ind][0]= entry.getKey();
		 				aux_repeticiones[ind][1]= entry.getValue().toString();
		 				ind++;		
		 		}
		 		
		 		// Se ordena la lista seg�n las apariciones de las palabras.
		 		for (int i = 0; i < palabras_sin_duplicar.size(); i++){
		            for(int j = i + 1; j < palabras_sin_duplicar.size(); j++){
		                if(Integer.parseInt(aux_repeticiones[i][1]) < Integer.parseInt(aux_repeticiones[j][1])){
		                	String aux4 = aux_repeticiones[i][0];
		                    String aux3 = aux_repeticiones[i][1];
		                    // Guardamos �ndice ordenado.
		                    aux_repeticiones[i][0] = aux_repeticiones[j][0];
		                    aux_repeticiones[j][0] = aux4;
		                    // Guardamos n�mero de caracteres de las frases ordenado.
		                    aux_repeticiones[i][1] = aux_repeticiones[j][1];
		                    aux_repeticiones[j][1] = aux3;
		                 }     
		            }
		 		}
		 		
		 		// Se ordena la lista seg�n el orden alfab�tico de las palabras con el mismo n�mero de apariciones.
		 		for (int i = 0; i < palabras_sin_duplicar.size(); i++){
		            for(int j = i + 1; j < palabras_sin_duplicar.size(); j++){
		            	if(Integer.parseInt(aux_repeticiones[i][1]) == Integer.parseInt(aux_repeticiones[j][1])){
		                	if (aux_repeticiones[i][0].compareTo(aux_repeticiones[j][0]) > 0) {
		                		String aux6 = aux_repeticiones[i][0];
			                    String aux5 = aux_repeticiones[i][1];
			                    aux_repeticiones[i][0] = aux_repeticiones[j][0];
				                aux_repeticiones[j][0] = aux6;
				                    // Guardamos n�mero de caracteres de las frases ordenado.
				                aux_repeticiones[i][1] = aux_repeticiones[j][1];
				                aux_repeticiones[j][1] = aux5;
		                	}
		            	}
		            }
		 		}
		 		
		    	// Mostrar las frases ordenadas seg�n n�mero de letras de menos a m�s.
		 		System.out.println("------------------------------------------------------------");
		 		System.out.println("Las frases ordenadas seg�n n�mero de letras son:");
		       
		    	for(m = 0; m< num_frases; m++) {
		            System.out.print("Frase 1: ");
		            System.out.print(frases_punto.get(long_frases[m][0]));
		            System.out.println(); 
		        }
		    	
		    	System.out.println("------------------------------------------------------------");
		    	for (int i=0; i<aux_repeticiones.length;i++)
	 					System.out.println("\"" + aux_repeticiones[i][0] + "\"" + " aparece " + aux_repeticiones[i][1] + " veces."); 					
	 }
}











/**
 * La clase encapsula en un array
 * una lista de numeros
 * 
 * @author - Jon Martinez
 * 
 */
import java.util.Arrays;

public class ListaNumeros 
{
    public static final int TAM_LISTA = 16;
    private int[] numeros;  
    private int pos;  

    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo del array
     */
    public ListaNumeros(int n) 
    {
        if (n > TAM_LISTA) {
            throw new IllegalArgumentException("Valor no permitido para tamaño lista");
        }
        else{
            numeros = new int[n];
            pos = 0;
        }
    }

    /**
     * @param numero el valor que se añade al final de numeros. No se hace nada si
     *               el array está completo o ya está el elemento
     * @return true si se ha podido añadir, false en otro caso
     * 
     * asumimos que numero es >= 0 (no hay que comprobarlo)
     */
    public boolean addElemento(int numero)
    {
        boolean añado = false;
        if (estaCompleta() || estaElemento(numero)){   
            añado = false;
        }
        else{
            numeros[pos] = numero;
            pos++;
            añado = true;
        }
        return añado;

    }

    /**
     * devuelve true si numeros está completo, false en otro caso Hazlo sin if
     */
    public boolean estaCompleta()
    {
        boolean completa = false;
        for(int i = numeros.length;pos == i;i ++){
            completa = true;
        }
        return completa;
    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. Hazlo sin if
     */
    public boolean estaVacia() 
    {
        boolean vacia = false;
        for(int i = 0;pos == i;i ++){
            vacia = true;
        }
        return vacia;

    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros()
    {
        return pos;

    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() 
    {
        for(int i = 0;i < pos;i ++){
            numeros[i] = 0;
        }
        pos = 0;
    }

    /**
     * @param numero el valor a buscar
     * @return true si se encuentra, false en otro caso
     */
    public boolean estaElemento(int numero) 
    {
        boolean elemento = false;
        for(int i = 0;i < pos;i ++){
            if(numeros[i] == numero){
                elemento = true;
            }
        }
        return elemento;
    }

    /**
     * Representación textual de la lista de la forma indicada  (ver enunciado)
     * Si numeros = {14, 8, 13, 9, 11, 5, 3, 10, 7, 1}
     *  devuelve | 14 | 8 | 13 | 9 | 11 | 5 | 3 | 10 | 7 | 1 |
     * 
     * Si la lista está vacía devuelve | |
     */
    public String toString() 
    {
        String linea = "| ";
        for(int i = 0; i < numeros.length; i ++){
            linea += numeros[i];
            linea += " | ";
        }
        return linea;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() 
    {
        System.out.println(this.toString());
    }

    /**
     * a partir de un array de pares contador/valor crea y devuelve
     * un nuevo array resultado de expandir estos pares contador/valor
     *  
     *   Si numeros =  {3, 8, 4, 2, 0, 42, 5, 1}
     *                  |  |  |  |  |   |  |  | 
     *                  +--+  +--+  +---+  +--+ 
     *                  par    par    par   par 
     * 
     *  se devuelve: {8, 8, 8, 2, 2, 2, 2, 1, 1, 1, 1, 1}
     * (ver detalles en el enunciado)
     */
    public int[] expandir() {
        int h = 0;
        for(int i = 0;i < pos;i += 2){
            h += numeros[i]; 
        }
        int[] expandido = new int[h];
        int k = 0;
        if(esImpar(numeros.length)){
            throw new RuntimeException("Nº impar de elementos en el array, añada uno más");
        }
        else{
            for(int i = 0; i < pos; i += 2){
                for(int j = 0; j < numeros[i];j ++){
                    expandido[k] = numeros[i + 1];
                    k ++;
                }
            }
        }
        return expandido;
    }

    /**
     * @param valor el nº a analizar
     * @return true si valor es impar, false en otro caso
     */
    private static boolean esImpar(int valor) {
        return valor % 2 != 0;
    }

    /**
     *  Modifica la lista reorganizando los valores pares e impares, los pares se
     *  colocan al principio y los impares al final. Se mantiene el orden de ambos
     *  
     *  Se hará recorriendo una sola vez la lista y sin  usar ningún otro array auxiliar
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13} 
     *  después de reorganizarParesImpares() quedaría {4, 2, 8, 3, 7, 9, 5, 11, 13}
     */
    public void reorganizarParesImpares() {
        int c = 0;
        int aux = 0;
        for(int i = 0;i < pos;i ++){
            if(!esImpar(numeros[i])){
                aux = numeros[c];
                numeros[c] = numeros[i];
                numeros[i] = aux;
                c ++;
            }
        }
    }

     
    /**
     *  Usando métodos de la clase Arrays haz una copia 
     *  de numeros al tamaño indicado por su longitud lógica
     *  Ordena esta copia
     *  Crea y devuelve un nuevo objeto ListaNumeros 
     *  que incluya los elementos del array ordenado
     */
    public ListaNumeros nuevaLista() {
        int[] copia = Arrays.copyOf(numeros, pos);
        Arrays.sort(copia);
        ListaNumeros listaNumerosNueva = new ListaNumeros(pos);
        for( int i = 0; i < pos; i++){
            numeros[i] = copia[i];
        }
        return listaNumerosNueva;
    }

    /**
     * devuelve un array de 2 dimensiones de 4 filas y 4 columnas  
     * y guarda en este array los elementos de numeros tal como indica el enunciado
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13}
     *  el nuevo array tendrá { {3, 7, 4, 9},
     *                          {2, 5, 8, 11} ,
     *                          {13, 0, 0, 0} ,
     *                          {0, 0, 0, 0} }
     * 
     */
    public int[][] toArray2D() 
    {
        int[][] array2D = new int[4][4];
        int i = 0;
        for(int f = 0;f < array2D.length; f ++){
            for(int c = 0;c < array2D[f].length; c ++){
                    array2D[f][c] = numeros[i];
                    i ++;
                    if(i == pos){
                        return array2D;
                    }
            }
        }
        return array2D;
    }

    /**
     * Punto de entrada a la aplicación
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) 
    {
        ListaNumeros numeros = new ListaNumeros(10);
        numeros.addElemento(3);
        numeros.addElemento(7);
        numeros.addElemento(4);
        numeros.addElemento(9);
        numeros.addElemento(2);
        numeros.addElemento(5);
        numeros.addElemento(8);
        numeros.addElemento(11);

        System.out.println("Original: " + numeros.toString());
        int[] expandido = numeros.expandir();
        System.out.println("Expandido: " + Arrays.toString(expandido));
        // seguir completando
        
        
        
    }
}

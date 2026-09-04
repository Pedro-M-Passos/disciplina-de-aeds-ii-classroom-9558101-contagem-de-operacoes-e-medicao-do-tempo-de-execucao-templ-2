import java.util.Random;
import java.util.function.Consumer;


/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    /**
     * Código de teste 1. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            operacoes++;
            resposta += vetor[i]%2;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                operacoes++;
                contador++;
            }

        }
        return contador;
    }

    /**
     * Código de teste 3. Este método...
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Este método...
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa...
     */
    static int codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    static void executarCodigo1() {

        for (int tamanho : tamanhosTesteGrande) {

            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo1(vetor);
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println(
                "Tamanho: " + tamanho +
                " | Operacoes: " + operacoes +
                " | Tempo: " + tempo + " ms"
            );

        }
    }

    static void executarCodigo2() {

        for (int tamanho : tamanhosTesteGrande) {

            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo2(vetor);
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println(
                "Tamanho: " + tamanho +
                " | Operacoes: " + operacoes +
                " | Tempo: " + tempo + " ms"
            );

        }
    }

    static void executarCodigo3() {

        for (int tamanho : tamanhosTesteMedio) {

            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo3(vetor);
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println(
                "Tamanho: " + tamanho +
                " | Operacoes: " + operacoes +
                " | Tempo: " + tempo + " ms"
            );

        }
    }

    static void executarCodigo4() {


        for (int n : tamanhosTestePequeno) {

            operacoes = 0;
            long inicio = System.nanoTime();
            codigo4(n);
            long fim = System.nanoTime();
            double tempo = (fim - inicio) * nanoToMilli;
            System.out.println(
                "n: " + n +
                " | Operacoes: " + operacoes +
                " | Tempo: " + tempo + " ms"
            );

        }
    }

    public static void testaVetores(int[] tamanhosVetores, Consumer<int[]> codigo) {
        
        int tamVetor;
        int[] vetor;
        long inicio, termino;
        double duracao;

        for(int i = 0; i < tamanhosVetores.length; i++) {
            tamVetor = tamanhosVetores[i];
            vetor = gerarVetor(tamVetor);
            operacoes = 0;
            inicio = System.nanoTime();
            codigo.accept(vetor);
            termino = System.nanoTime();
            duracao = (double)(termino - inicio) * NANO_TO_MILLI;
            System.out.printf("%,d; %,d; %,.2f ms\n", tamVetor, operacoes, duracao);

        }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    }

    static int[] bubbleSort(int[] vetor) {
        int n = vetor.length;
        boolean trocou = true;

        for (int i = n - 1; (i > 0 && trocou); i--) {
            trocou = false;
            for (int j = 0; j < n - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    // Troca os elementos
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
        return vetor;
    }


    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
        
    }
    public static void main(String[] args) {
        

    executarCodigo1();
    executarCodigo2();
    executarCodigo3();
    executarCodigo4();


    }
}

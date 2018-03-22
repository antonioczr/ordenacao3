/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author antonio
 */
public class AlgoritmosOrd {

    private static ArrayList<Integer> readFile(String fileName) { //lê o arquivo até o final e salva os dados
        ArrayList<Integer> dados_arq = new ArrayList<Integer>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                dados_arq.add(Integer.parseInt(scanner.next()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dados_arq;
    }

    /*      SELECTION SORT      */
    public static void selectionSort(ArrayList<Integer> vetor) {

        int menor, indiceMenor;

        for (int i = 0; i < vetor.size() - 1; i++) { //armazena o primeiro elemento e atribui o indice a ele
            menor = vetor.get(i);
            indiceMenor = i;

            for (int j = i + 1; j < vetor.size(); j++) { //faz a comparação e atribui o novo menor valor
                if (vetor.get(j) < menor) {
                    menor = vetor.get(j);
                    indiceMenor = j;
                }
            }

            //faz as trocas
            vetor.set(indiceMenor, vetor.get(i));
            vetor.set(i, menor);

        }
    }

    /*      INSERTION SORT      */
    public static void insertionSort(ArrayList<Integer> vetor) {
        for (int i = 1; i < vetor.size(); i++) {
            int hold = vetor.get(i);
            int j = i;

            while (j > 0 && vetor.get(j - 1) > hold) {
                vetor.set(j, vetor.get(j - 1));
                j--;
            }

            vetor.set(j, hold);
        }
    }

    /*      MERGE SORT      */
    public static void mergeSort(ArrayList<Integer> vetor, ArrayList<Integer> auxiliar, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2; //acha o indice do elemento do meio do vetor
            mergeSort(vetor, auxiliar, inicio, meio); //mergeSort chamado recursivamente para uma metade do vetor
            mergeSort(vetor, auxiliar, meio + 1, fim); //mergeSort chamado recursivamente para outra metade do vetor
            intercalar(vetor, auxiliar, inicio, meio, fim); //chama o metodo para intercalar
        }
    }

    /*      QUICK SORT      */
    private static void quickSort(ArrayList<Integer> vetor, int esquerda, int direita) {
        if (esquerda < direita) {
            int pivo = separar(vetor, esquerda, direita);
            quickSort(vetor, esquerda, pivo - 1);
            quickSort(vetor, pivo + 1, direita);
        }
    }

    /*      HEAP SORT       */
    public static void heapSort(ArrayList<Integer> vetor) {
        buildMaxHeap(vetor);
        int n = vetor.size();

        for (int i = vetor.size() - 1; i > 0; i--) {
            swap(vetor, i, 0);
            maxHeapify(vetor, 0, --n);
        }
    }

    private static void buildMaxHeap(ArrayList<Integer> vetor) {
        for (int i = vetor.size() / 2 - 1; i >= 0; i--) {
            maxHeapify(vetor, i, vetor.size());
        }
    }

    private static void maxHeapify(ArrayList<Integer> vetor, int pos, int tamanhoDoVetor) {
        int max = 2 * pos + 1, direita = max + 1;
        if (max < tamanhoDoVetor) {
            if (direita < tamanhoDoVetor && vetor.get(max) < vetor.get(direita)) {
                max = direita;
            }

            if (vetor.get(max) > vetor.get(pos)) {
                swap(vetor, max, pos);
                maxHeapify(vetor, max, tamanhoDoVetor);
            }
        }
    }

    public static void swap(ArrayList<Integer> vetor, int j, int aposJ) {
        int aux = vetor.get(j);
        vetor.set(j, vetor.get(aposJ));
        vetor.set(aposJ, aux);
    }

    private static void intercalar(ArrayList<Integer> vetor, ArrayList<Integer> auxiliar, int inicio, int meio, int fim) {
        for (int k = inicio; k <= fim; k++) {
            auxiliar.add(k, vetor.get(k));
        }

        int i = inicio;
        int j = meio + 1;

        for (int k = inicio; k <= fim; k++) {
            if (i > meio) {
                vetor.set(k, auxiliar.get(j++));
            } else if (j > fim) {
                vetor.set(k, auxiliar.get(i++));
            } else if (auxiliar.get(i) < auxiliar.get(j)) {
                vetor.set(k, auxiliar.get(i++));
            } else {
                vetor.set(k, auxiliar.get(j++));
            }
        }

    }

    private static int separar(ArrayList<Integer> vetor, int esquerda, int direita) {
        int pivo = vetor.get(direita), i = esquerda;

        for (int j = esquerda; j <= direita - 1; j++) {
            if (vetor.get(j).compareTo(pivo) < 1) {
                int aux = vetor.get(i);
                vetor.set(i, vetor.get(j));
                vetor.set(j, aux);
                i += 1;
            }
        }

        int aux = vetor.get(i);
        vetor.set(i, vetor.get(direita));
        vetor.set(direita, aux);

        return i;
    }

    public static void qsort(ArrayList<Integer> vetor) {
        int inicio = 0;
        int fim = vetor.size() - 1;
        quickSort(vetor, inicio, fim);
    }

    public static void main(String[] args) {

        ArrayList<Integer> dados_arquivo = readFile("arq.txt");
        ArrayList<Integer> auxiliar = new ArrayList<Integer>();
        //selectionSort(dados_arquivo);
        //insertionSort(dados_arquivo);
        //mergeSort(dados_arquivo, auxiliar, 0, dados_arquivo.size() - 1);
        //qsort(dados_arquivo);
        heapSort(dados_arquivo);

        for (int n : dados_arquivo) {
            System.out.println(n);
        }
    }

}

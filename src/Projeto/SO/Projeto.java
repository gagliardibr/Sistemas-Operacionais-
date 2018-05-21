//
//Escreva um programa usando threads que receba dois vetores e execute a soma paralela
//
package Projeto.SO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Projeto {
    //instancia o metodo random
    private static final Random num = new Random();
   
    private static int varCompartilhada = 0;
    private static final Integer cont = 5;
    private static int soma = 0;
    
    private static final List<Integer> array1 = new ArrayList<>();
    private static final List<Integer> array2 = new ArrayList<>();
    
    
    public static void main(String[] args) {

        //criação da thread
        Thread t1 = new Thread(() -> {
            //loop para estipular quantos número serão colocado no array
            for (int i = 0; i < cont; i++) {
                //chamada da função que incremente o array
                incrementaArray1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < cont; i++) {
                incrementaArray2();
            }
        });

        t1.start();
        t2.start();

        try {
            //Esse metedo congela a execução da thread corrente e 
            //aguarda a conclusão da thread na qual esse método foi invocado
            t1.join();
            t2.join();

        } catch (InterruptedException ex) {
        }
    }

    //função que imcrementa dentro do array
    private synchronized static void incrementaArray1() {
        //soteia um novo número para ser colocado array
        varCompartilhada = num.nextInt(10) + 1;
        
        //adiciona no array
        array1.add(varCompartilhada);
        
        //imprime o número adionado
        System.out.println("Array 1: " + array1);
        
        //faz a soma do número 
        soma += varCompartilhada;
        
        //imprime soma
        System.out.println("Soma: " + soma + "\n");

    };
    
     private synchronized static void incrementaArray2() {
        varCompartilhada = num.nextInt(10) + 1;
        array2.add(varCompartilhada);
        System.out.println("Array 2: " + array2);
        soma += varCompartilhada;
        System.out.println("Soma: " + soma + "\n");
    }

}

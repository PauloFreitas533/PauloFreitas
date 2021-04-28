
package com.mycompany.algoritmogenetico;

public class Principal {
    private static double[] pop;
    private static String[] bin;
    private static int[] dec;
    private static double[] fit;
    private static double taxaCross = 0.7;
    private static double taxaDeMut = 0.01;
    private static int ind = 500;
    private static int genes = 8;
    
    public static void main(String[] args) {
        gerarPopInicial();
        fit = new double[ind];
        for (int j = 0; j < fit.length; j++){ fit[j] = fit(dec[j]); /*System.out.println(fit[j]);*/}
        for (int i = 0; i < genes*ind; i++) {
            int pai = paiMenor();
            int mae = maeMaior(pai);
            taxaCross(pai,mae);
            mutacao(pai,mae);
            for (int j = 0; j < ind; j++) dec[j] = binarioToDecimal(bin[j]);
            for (int j = 0; j < fit.length; j++){ fit[j] = fit(dec[j]); /*System.out.println(fit[j]);*/}
        }
        System.out.println("Resultado genético");
        System.out.println("(x) = "+dec[paiMaior()]);
        System.out.println("f(x) = "+fit(dec[paiMaior()]));
    }
    
    public static void gerarPopInicial() {
        int espaco = ind*genes, cont = 0;
        pop = new double[espaco];
        dec = new int[ind];
        bin = new String[ind];
        for (int i = 0; i < espaco; i++) {
            pop[i] = Math.random();
        }
        
        for (int i = 0; i < ind; i++) {
            String binario = "";
            for (int j = 0; j < genes; j++) {
                binario += (int) Math.round(pop[cont]);
                cont ++;
                bin[i] = binario;
            }
            dec[i] = binarioToDecimal(binario);
        }
    }
    
    public static int binarioToDecimal(String binario) {
        char[] corrente1 = binario.toCharArray();
        char[] corrente = new char[corrente1.length];
        for (int i = 0; i < corrente1.length; i++) corrente[corrente1.length-i-1] = corrente1[i];
        int valor = 0, resultado = 0, aux = 0;
        for (int i = 0; i < corrente.length; i++) {
            valor = aux * 2;
            if (valor == 0) valor = 1;
            if (corrente[i] == '1') {
                resultado += valor;
            }
            aux = valor;
        }
        return resultado;
    }
    
    public static double fit(int x) {return (x*x)-(18*x)+24;}
    
    public static int paiMaior() {
        double maior = fit[0];
        int posicao = 0;
        for (int i = 1; i < fit.length; i++) {
            if (fit[i] > maior){
                maior = fit[i];
                posicao = i;
            }
        }
        return posicao;
    }
    
    public static int maeMaior(double pai) {
        double maior = 0; int posicao = 0;
        if (pai == 0) maior = fit[1];
        else maior = fit[0];
        for (int i = 0; i < fit.length; i++) {
            if (fit[i] > maior && i != pai) {
                maior = fit[i];
                posicao = i;
            }
        }
        return posicao;
    }
    
    public static int paiMenor() {
        double menor = fit[0];
        int posicao = 0;
        for (int i = 1; i < fit.length; i++) {
            if (fit[i] < menor){
                menor = fit[i];
                posicao = i;
            }
        }
        return posicao;
    }
    
    public static int maeMenor(double pai) {
        double menor = 0; int posicao = 0;
        if (pai == 0) menor = fit[1];
        else menor = fit[0];
        for (int i = 0; i < fit.length; i++) {
            if (fit[i] < menor && i != pai) {
                menor = fit[i];
                posicao = i;
            }
        }
        return posicao;
    }
    
    public static void taxaCross(int posicaoPai, int posicaoMae) {
        if (Math.random() < taxaCross) {
            char[] binarioPai = bin[posicaoPai].toCharArray();
            char[] binarioMae = bin[posicaoMae].toCharArray();
            String corrente = "";
            int taxaCross = Math.round(binarioPai.length/2);
            for (int i = 0; i < taxaCross; i++) corrente += binarioPai[i];
            taxaCross = binarioMae.length - taxaCross;
            for (int i = taxaCross; i < binarioMae.length; i++) corrente += binarioMae[i];
            bin[0] = corrente;
        }

    }
    
    public static void mutacao(int posicaoPai, int posicaoMae) {
        if (Math.random() < taxaDeMut) {
            char[] binarioPai = bin[posicaoPai].toCharArray();
            int a = (binarioPai.length) -1;
            int posicao = (int) Math.round(Math.random() * a);
            if (binarioPai[posicao] == '0') binarioPai[posicao] = '1'; else binarioPai[posicao] = '0';
            bin[1] = String.valueOf(binarioPai);
            }
        if (Math.random() < taxaDeMut) {
            char[] binarioMae = bin[posicaoMae].toCharArray();
            int a = (binarioMae.length) -1;
            int posicao = (int) Math.round(Math.random() * a);
            if (binarioMae[posicao] == '0') binarioMae[posicao] = '1'; else binarioMae[posicao] = '0';
            bin[2] = String.valueOf(binarioMae);
            
        }
        
    }
}

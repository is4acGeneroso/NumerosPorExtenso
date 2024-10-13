package codigo;

public class Extenso {
    private String numeroPorExtenso;

    public String transformarEmExtenso(int numeroDigitado) {
        String numeroPorExtenso = "";
        
        if(numeroDigitado < 10) {
            numeroPorExtenso = unidade(numeroDigitado);
        } else if(numeroDigitado < 20) {
            numeroPorExtenso = excessao(numeroDigitado);
        } else if(numeroDigitado < 100) {
            numeroPorExtenso = dezena(numeroDigitado);
        } else if(numeroDigitado < 1000) {
            numeroPorExtenso = centena(numeroDigitado);
        } else {
            numeroPorExtenso = "CALMA FILHÃO DEPOIS CONTINUO!";
        }
        
        this.setNumeroPorExtenso(numeroPorExtenso);
        return this.getNumeroPorExtenso();
    }
    
    //UNIDADE 0 - 9
    public String unidade(int numeroDigitado) {
        String[] unidade = {"Zero", "Um", "Dois", "Tres", "Quatro", "Cinco", "Seis", "Sete", "Oito", "Nove"};
        int posicaoUnidade = numeroDigitado; 
        
        return unidade[posicaoUnidade]; //retorna unidade na posicao digitada
    }

    //EXCESSAO 10 - 19
    public String excessao(int numeroDigitado) {
        String[] excessao = {"Dez", "Onze", "Doze", "Treze", "Quatorze", "Quinze", "Dezesseis", "Dezessete", "Dezoito", "Dezenove"};
        int posicaoExcessao = numeroDigitado - 10; //(10 -> 19) - 10 = posicao de 0 a 9 acima
        
        return excessao[posicaoExcessao];
    }
    
    //DEZENA 20 - 99
    public String dezena(int numeroDigitado) {
        String[] dezena = {"", "Dez", "Vinte", "Trinta", "Quarenta", "Cinquenta", "Sessenta", "Setenta", "Oitenta", "Noventa"};
        boolean dezenaUnica = (numeroDigitado % 10 == 0)? true: false; //a variavel verifica se o numero é (20, 30, 40, 50, 60, 70, 80, 90 - numeros sem unidades)
        int posicaoDezena = numeroDigitado / 10; //resultado de divisao inteira ex: 25/10 = 2 ou 72/10 = 7 (atribui apenas a dezena) 
        
        if(dezenaUnica) { //se o numero nao tiver unidade ou unidade = 0
            return dezena[posicaoDezena]; //retorna apenas ele em extenso
        } else { 
            int posicaoUnidade = 0;
            String numeroComposto = "";
            posicaoUnidade = numeroDigitado - (posicaoDezena * 10); //ex: 23 - (2 * 10) = (3) entao temos 3 unidades no numero 23
            
            numeroComposto = String.format("%s e %s", dezena[posicaoDezena], unidade(posicaoUnidade)); //retorna a dezena + o que esta dentro da posicao da array na funcao unidade()
            
            return numeroComposto;
        }
    }
    
    //CENTENA 100 - 999
    //Para este metodo temos 4 hipoteses a se tratar
    //900 - Centena Unica
    //912 - Centena + Excessao 
    //920 - Centena + Dezena
    //999 - Centena + Dezena + Unidade
    public String centena(int numeroDigitado) {
        String[] centena = {"", "Duzentos", "Trezentos", "Quatrocentos", "Quinhentos", "Seiscentos", "Setecentos", "Oitocentos", "Novecentos"};
        boolean centenaUnica = (numeroDigitado % 100 == 0)? true: false; //verifica se a centena é unica ex: 100, 200, 300, 400
        int numeroSemCentena = numeroDigitado - numeroDigitado / 100 * 100; //calculo para retirar a centena do numero ex: 912 = 12
        boolean centenaExcessao = (numeroSemCentena > 9 && numeroSemCentena < 20)? true: false; //se o numero sem centena estiver no intervelo de 10 a 19 ira ser tratado como excessao
        boolean centenaDezena = (numeroSemCentena % 10 == 0)? true: false; //verifica se o numero possui apenas centena e dezena ex: 190, 240, 430
        int posicaoCentena = numeroDigitado / 100 - 1; //calculo para encontrar a posicao da centena ex: (912 / 100 divisaoInteira = 9) - 1 = 8 | centena[8] = "Novecentos" 
        centena[0] = (!centenaUnica)? "Cento": "Cem"; //se o numero nao for centena unica nao é mais cem e sim cento ex: 101 não é (cem e um) e sim (cento e um)
        int posicaoDezena = numeroDigitado - (numeroDigitado / 100 * 100); //linha 50 
        int posicaoExcessao = (centenaExcessao)? posicaoDezena: 0; //linha 68 for verdadeira atribui o numero contido na excessao à posicao excessao
        
        String numeroComposto = "";
        
        if(centenaUnica) { //se for centena unica
            return centena[posicaoCentena]; //
        } else if(centenaExcessao) { //se for centena + excessao
            numeroComposto = String.format("%s e %s", centena[posicaoCentena], excessao(posicaoExcessao));
            
            return numeroComposto;
        } else if(centenaDezena) { //se for centena + dezena (sem unidade) ex: 240, 320, 590 
            numeroComposto = String.format("%s e %s", centena[posicaoCentena], dezena(posicaoDezena));
            
            return numeroComposto;
        } else { //se for um numero com unidade, dezena, centena ex: 999, 888, 657
            numeroComposto = String.format("%s e %s", centena[posicaoCentena], dezena(posicaoDezena));
            
            return numeroComposto;
        }   
    }
    
    //METODOS ACESSORES
    public String getNumeroPorExtenso() {
        return numeroPorExtenso;
    }

    public void setNumeroPorExtenso(String numeroPorExtenso) {
        this.numeroPorExtenso = numeroPorExtenso;
    }
}

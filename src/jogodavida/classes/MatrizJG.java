/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavida.classes;

import java.util.Date;
import java.util.Timer;
import javax.swing.JFrame;
import jogodavida.gui.DesenhaGrade;

/**
 *
 * @author Tiago B
 */
public class MatrizJG extends JFrame
{
    int[][] Matriz = new int[30][30];
    int[][] MatrizAuxiliar = new int[30][30];
    
    DesenhaGrade objDesenha = new DesenhaGrade();
    
    public MatrizJG()
    {//Iniciar o estado da matriz.
        
	for(int contadory = 0;contadory<30;contadory++)
	{
            for(int contadorx = 0;contadorx<30;contadorx++)
            {				
                Matriz[contadory][contadorx] = 0;     
            }           
        }
        //Valores iniciais atribuidos a matriz. 
        Matriz[0][0] = 1;
        Matriz[0][1] = 0;
        Matriz[1][1] = 1;
        Matriz[8][7] = 0;
        Matriz[8][9] = 1;
        Matriz[8][8] = 0;
        Matriz[8][6] = 1;
	//Matriz[28][29] = 1;
        Matriz[6][6] = 1;
        //Matriz[6][5] = 1;
        Matriz[6][7] = 0;
        Matriz[6][8] = 1;
        Matriz[6][4] = 1;
        Matriz[6][2] = 1;
        Matriz[6][1] = 1;
        Matriz[6][9] = 1;
        Matriz[7][7] = 0;
        Matriz[7][8] = 1;
        Matriz[7][9] = 0;
        
        Matriz[4][10] = 0;
        Matriz[4][10] = 1;
        Matriz[10][4] = 0;
        Matriz[10][2] = 0;
        Matriz[4][1] = 1;
        Matriz[5][9] = 1;
        Matriz[3][7] = 0;
        Matriz[2][8] = 1;
        Matriz[1][9] = 0;
       // Matriz[0][29] = 1;
        System.out.println("Estágio inicial da matriz");
	for(int contadory = 0;contadory<30;contadory++)
	{
            for(int contadorx = 0;contadorx<30;contadorx++)
            {                
		System.out.print(" "+Matriz[contadory][contadorx]+" ");
            }
            System.out.println();
	}
        
        ImprimeMatriz(Matriz);
        System.out.println();        
    }///construtor
    
    public void ImprimeMatrizCoord()
    {
	for(int contadory = 0;contadory<30;contadory++) //coord. X
	{
            for(int contadorx = 0;contadorx<30;contadorx++)// coord. Y
            {                
		//System.out.print(" "+Matriz[contadorx][contadory]+" ");
                System.out.print(" ("+contadory+","+contadorx+") ");
            }
            System.out.println();
	}
        
        System.out.println();
    }//fim do metodo ImprimeMatriz.    
    
    public void ImprimeMatriz(int[][] MatrizImp)
    {
	for(int contadory = 0;contadory<30;contadory++)
	{
            for(int contadorx = 0;contadorx<30;contadorx++)
            {                
		System.out.print(" "+MatrizImp[contadory][contadorx]+" ");
                //System.out.print(" ("+contadorx+","+contadory+") ");
            }
            System.out.println();
	}
        
        try{
            Thread.sleep(1000);
            objDesenha.setMatriz(MatrizImp);
            objDesenha.Desenha();
        }catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
       // objDesenha.setMatriz(MatrizImp);
        //objDesenha.Desenha();
        
        System.out.println();
    }//fim do metodo ImprimeMatriz.    
    
    public void ProcessaMatriz()
    {
        int posicaoatualx = 0;
        int posicaoatualy = 0;
        int estado = 0;
        //MatrizAuxiliar = Matriz;
        CopiaValoresMatriz(Matriz, MatrizAuxiliar);
        /**
         * Contador de ciclo de vida das celulas, determina a quantidade de 
         * evoluções, de interações que terá o jogo, esta setado com 40.
        */
        for (int contadorciclo = 1; contadorciclo < 40; contadorciclo++) 
        {
            System.out.println("Interação numero : "+String.valueOf(contadorciclo));
            //Laço que percorrerá a matriz.         
            if(contadorciclo > 1)
            {
                CopiaValoresMatriz(Matriz, MatrizAuxiliar);
            }
           
            for(int contadory = 0;contadory<30;contadory++)
            {
                for(int contadorx = 0;contadorx<30;contadorx++)
                {             
                    posicaoatualx = contadorx;
                    posicaoatualy = contadory;
                    estado = Matriz[posicaoatualy][posicaoatualx];
                    //MatrizAuxiliar[contadorx][contadory] = estado;
                    //MatrizAuxiliar[contadory][contadorx] = VerificaVizinhos(posicaoatualx, posicaoatualy, estado);
                    if((contadory == 1) && (contadorx == 1))
                    {
                        //MatrizAuxiliar[contadory][contadorx] = RetornaEstado(posicaoatualy, posicaoatualx, estado);
                        SetMatriz(contadory, contadorx, RetornaEstado(posicaoatualy, posicaoatualx, estado));
                    }
                    else
                    {
                        //MatrizAuxiliar[contadory][contadorx] = RetornaEstado(posicaoatualy, posicaoatualx, estado);
                        SetMatriz(contadory, contadorx, RetornaEstado(posicaoatualy, posicaoatualx, estado));
                    }
                }
            }
            
           
        //    CopiaValoresMatriz(MatrizAuxiliar, Matriz);
           // ImprimeMatriz(Matriz);        
            System.out.println("-----");
            ImprimeMatriz(MatrizAuxiliar);
          //  LimpaMatriz(MatrizAuxiliar);
        }   
            
    }//metodo processa matriz.
    
    public int RetornaEstado(int coordx, int coordy, int estadoatual)//Retorna o estado da matriz
    {
        int estadosaida = 0;
        int quantidadevizinhos = 0;//Variavel que recebe a quantidade de vizinhos da celula.
        
        //Verificação dos pontos extremos.{(0,0),(0,29),(29,0),(29,29)}
        if((coordx == 0) && (coordy == 0))
        {
            quantidadevizinhos = VerificaPontosExtremos(coordx, coordy);
        }
        else
        {
            if((coordx == 0) && (coordy == 29))
            {
                quantidadevizinhos = VerificaPontosExtremos(coordx, coordy);
            }
            else
            {
                if((coordx == 29) && (coordy == 0))
                {
                    quantidadevizinhos = VerificaPontosExtremos(coordx, coordy);
                }
                else
                {
                    if((coordx == 29) && (coordy == 29))
                    {
                        quantidadevizinhos = VerificaPontosExtremos(coordx, coordy);
                    }                    
                }
            }
        }
        //----------------------------------------------------------------------
            
        //Verificação das linhas extremas.{(0,inc(x)) - parte de cima,(29,inc(x)) - parte de baixo,(y,0) - lado esquerdo ,(y,29) - lado direito}
        //Parte de cima do quadrante.
        if((coordx == 0) && ((coordy >0) && (coordy <=28)))
        {
            quantidadevizinhos = VerificaLinhasExtremas(coordx, coordy);
        }
        else
        {//Parte de baixo do quadrante.
            if((coordx == 29) && ((coordy >0) && (coordy <=28)))
            {
               quantidadevizinhos = VerificaLinhasExtremas(coordx, coordy);
            }
        }        
        
        if(((coordx > 0)&&(coordx <=28)) && (coordy == 0))
        {
            quantidadevizinhos = VerificaLinhasLaterais(coordx, coordy);
        }
        else
        {
          /*  if((coordx > 0) && (coordy == 0))
            {
                quantidadevizinhos = VerificaLinhasLaterais(coordx, coordy);
            }
            else{*/
                if(((coordx > 0)&&(coordx<=28)) && (coordy == 29))
                {
                    quantidadevizinhos = VerificaLinhasLaterais(coordx, coordy);
                }
            //}
        }
       
        //----------------------------------------------------------------------
        
        //Verificação das demais coordenadas.
        if(((coordx > 0) && (coordx <= 28)) && ((coordy > 0)&&(coordy <= 28)))
        {
            quantidadevizinhos = VerificaPontosNormais(coordx, coordy);
        }
        //----------------------------------------------------------------------
                
        //Regra
        if(estadoatual == 1)
        {//Celula viva.
            //Regra 1 : Célula vida com menos de dois vizinhos morre de solidão.
            if(quantidadevizinhos < 2){
                estadosaida = 0;
            }
            else{
                //Regra 2: Célula viva com mais de três vizinhos morre de superpopulação.
                if(quantidadevizinhos > 3){
                    estadosaida = 0;
                }
                else{
                    //Regra 4: Célula com dois vizinhos continua no mesmo estado.
                    if(quantidadevizinhos == 2){
                        estadosaida = estadoatual;
                    }
                    
                }
            }
        }
        //Regra 3: Qualquer célula com exatamente três vizinhos vivos se torna célula viva.
        if(quantidadevizinhos == 3)
        {
            estadosaida = 1;
        }
            
        //Regra 4: Célula com dois vizinhos continua no mesmo estado.
        if((quantidadevizinhos == 2) && (estadoatual == 0))
        {
            estadosaida = estadoatual;
        }                
            
        
        //----------------------------------------------------------------------
        
        return estadosaida;//retorna o novo estado da celula.
    }//Retorna o estado da matriz
    
    public int VerificaPontosExtremos(int coordx, int coordy)
    {
        int contadorvizinhos = 0;
        int coordxaux = 0;
        int coordyaux = 0;
        int contador = 0;
        
        //Verificação dos extremos do quadro.
        if((coordx == 0) && (coordy == 0))
        {
            coordxaux = coordx;
            coordyaux = coordy;
            //Verifica o vizinho na mesma linha;
            if(MatrizAuxiliar[coordy][coordxaux+1] == 1){
                contadorvizinhos = contadorvizinhos+1;
            }
            //Verificaa a linha de baixo.
            coordyaux = coordyaux+1;
            while(contador<= coordyaux){
                if(MatrizAuxiliar[coordyaux][contador] == 1)
                {
                    contadorvizinhos = contadorvizinhos+1;
                }
                contador = contador + 1;
            }
        }
        else
        {
            if((coordx == 0) && (coordy == 29))
            {
                coordxaux = coordx;
                coordyaux = coordy;
               // System.out.println(String.valueOf(coordy));
                //Verifica o vizinho na mesma linha
                if(MatrizAuxiliar[coordx][coordyaux-1] == 1){
                    contadorvizinhos = contadorvizinhos+1;
                }
                coordxaux = coordxaux+1;
                contador = 28;
              //  System.out.println(String.valueOf(coordyaux));
                while(contador<= coordyaux){
                    if(MatrizAuxiliar[coordxaux][contador] == 1)
                    {
                        contadorvizinhos = contadorvizinhos+1;
                    }
                    contador = contador + 1;
                }                
            }
            else
            {
                if((coordx == 29) && (coordy == 0))
                {
                    coordxaux = coordx;
                    coordyaux = coordy;
                    if(MatrizAuxiliar[coordx][coordyaux+1] == 1)
                    {
                        contadorvizinhos = contadorvizinhos+1;
                    }
                    coordyaux = coordyaux+1;
                    coordxaux = coordxaux-1;
                    contador = 0;
                    while(contador<= coordyaux)
                    {
                        if(MatrizAuxiliar[coordxaux][contador] == 1)
                        {
                            contadorvizinhos = contadorvizinhos+1;
                        }
                        contador = contador + 1;
                    }                     
                }
                else
                {
                    if((coordx == 29) && (coordy == 29))
                    {
                        coordxaux = coordx;
                        coordyaux = coordy;
                        if(MatrizAuxiliar[coordxaux-1][coordy] == 1)
                        {
                            contadorvizinhos = contadorvizinhos+1;
                        }          
                        contador = 28;
                        coordxaux = coordxaux-1;
                        while(contador<=coordyaux)
                        {
                            if(MatrizAuxiliar[coordxaux][contador] == 1)
                            {
                                contadorvizinhos = contadorvizinhos+1;
                            }
                            contador = contador + 1;                            
                        }
                    }
                }
            }   
        }
        
        return contadorvizinhos;
    }//Verificar ponto extremo
    
    public int VerificaLinhasExtremas(int coordx, int coordy)
    {
        int contadorvizinhos = 0; 
        int coordxaux = 0;
        int coordyaux = 0;
        int contador = 0;
        
        coordxaux = coordx;
        coordyaux = coordy;
        //Verifica os vizinhos na mesma linha.
        //A esquerda
        if(MatrizAuxiliar[coordxaux][coordyaux-1] == 1){
            contadorvizinhos = contadorvizinhos + 1;
        }
        coordyaux = coordy;
        //A direita
        if(MatrizAuxiliar[coordx][coordyaux+1] == 1){
            contadorvizinhos = contadorvizinhos + 1;
        }
        
        if(coordx == 29)
        {
            coordxaux = coordxaux-1;
            contador = coordyaux-1;
            coordyaux = coordxaux;
        }
        else
        {
            coordxaux = coordxaux+1;
            contador = coordyaux-1;
        }
        
        while(contador<3)
        {
            if(MatrizAuxiliar[coordxaux][contador] == 1)
            {
                contadorvizinhos = contadorvizinhos+1;
            }
            contador = contador+1;
        }            

        return contadorvizinhos;
    }//Verifica Linhas extremas
    
    public int VerificaLinhasLaterais(int coordx, int coordy)
    {
        int contadorvizinhos = 0;
        int coordxaux = 0;
        int coordyaux = 0;
        int contador = 0;        
        
        coordxaux = coordx;
        coordyaux = coordy;
        
        
        //Lateral esquerda
        if(coordy == 0){
            if(MatrizAuxiliar[coordx][coordyaux+1] == 1)
            {
                contadorvizinhos = contadorvizinhos+1;
            }
            
            coordxaux = coordxaux-1;
            while(contador<2)//Parte de cima.
            {
                if(MatrizAuxiliar[coordxaux][contador] == 1)
                {
                    contadorvizinhos = contadorvizinhos+1;
                }
                contador = contador+1;
            }
            coordxaux = coordx;
            coordxaux = coordxaux+1;
            contador = 0;
            while(contador<2)
            {
                if(MatrizAuxiliar[coordxaux][contador] == 1)
                {
                    contadorvizinhos = contadorvizinhos+1;
                }
                contador = contador+1;
            }
            
        }
        else
        {
            if(coordy == 29)
            {
                if(MatrizAuxiliar[coordx][coordyaux-1] == 1)
                {
                    contadorvizinhos = contadorvizinhos+1;
                }

                coordxaux = coordxaux-1;
                contador = 28;
                while(contador<30)//Parte de cima.
                {
                    if(MatrizAuxiliar[coordxaux][contador] == 1)
                    {
                        contadorvizinhos = contadorvizinhos-1;
                    }
                    contador = contador+1;
                }
                coordxaux = coordx;
                coordxaux = coordxaux+1;
                contador = 28;
                while(contador<30)
                {
                    if(MatrizAuxiliar[coordxaux][contador] == 1)
                    {
                        contadorvizinhos = contadorvizinhos+1;
                    }
                    contador = contador+1;
                }                
            }
        }            
        
        
        return contadorvizinhos;
    }//VerificaLinhasLaterais
    
    public int VerificaPontosNormais(int coordx, int coordy)
    {
        int contadorvizinhos = 0;
        int coordxaux = 0;
        int coordyaux = 0;
        int contador = 0;   
        
        coordxaux = coordx;
        coordyaux = coordy;
        //Verifica os vizinhos na mesma linha
        if(MatrizAuxiliar[coordxaux][coordyaux-1] == 1)
        {
            contadorvizinhos = contadorvizinhos+1;
        }
        
        if(MatrizAuxiliar[coordxaux][coordyaux+1] == 1)
        {
            contadorvizinhos = contadorvizinhos+1;
        }        
        
        
        coordyaux = coordy;
        contador = coordy-1;        
        while(contador<=(coordy+1))
        {
            if(MatrizAuxiliar[coordxaux-1][contador] == 1)
            {
                contadorvizinhos = contadorvizinhos+1;
            }
             contador = contador+1;
        } 
        
        coordyaux = coordy;
        contador = coordy-1;        
        while(contador<=(coordy+1))
        {
            if(MatrizAuxiliar[coordxaux+1][contador] == 1)
            {
                contadorvizinhos = contadorvizinhos+1;
            }
             contador = contador+1;
        }         
        
        return contadorvizinhos;
    }//VerificaPontosNormais
    
    public void LimpaMatriz(int[][] Matriz)
    {
        for(int contadory = 0;contadory<30;contadory++)
	{
            for(int contadorx = 0;contadorx<30;contadorx++)
            {                
		MatrizAuxiliar[contadory][contadorx] = 0;
            }
	}        
    }//Metodo LimpaMatriz
    
    public void SetMatriz(int posx, int posy, int estado)
    {
        Matriz[posx][posy] = estado;
    }//SetMatriz
    
    public void CopiaValoresMatriz(int[][] MatrizOrigem, int[][] MatrizDestino)
    {
        for(int contadory = 0;contadory<30;contadory++)
        {
            for(int contadorx = 0;contadorx<30;contadorx++)
            {          
                MatrizDestino[contadory][contadorx] = MatrizOrigem[contadory][contadorx];
            }
        }
    }
    
//Funções antigas que serão subsituidas.    
    public int VerificaVizinhos(int posx, int posy, int estadocelula)
    {
        int estadosaida = 0;
        int ax,ay,bx,by,cx,cy;
        int contadorlinha = 0;
        int posicaoy = 0;
        int numerodevizinhos = 0;
 
        //Verifica a regra na primeira posição da matriz.
        if((posx == 0)&&(posy == 0))
        {
            if(estadocelula == 0)//(Célula morta)
            {
                numerodevizinhos = vizinhoCelula2(posx+1, 0, posx, posy)+vizinhoCelula3(posx, 1);
                //Regra: Qualquer célula morta com exatamente três vizinhos se torna uma célula viva.
                if(numerodevizinhos == 3)
                {
                    estadosaida = 1;
                }
               /* else
                {
                    if(numerodevizinhos == 2)//com dois vizinhos mantem o mesmo estado.
                    {
                        estadosaida = estadocelula;
                    }
                }*/

            }
            else
            {
                //(Célula viva)
                numerodevizinhos = vizinhoCelula2(posx+1, 0, posx, posy)+vizinhoCelula3(posx, 1);
                //Regra: Qualquer célula com mais de três vizinhos vivos morre de superpopulação.
                if(numerodevizinhos > 3)
                {
                    estadosaida = 0;
                }
                else
                {
                    //Regra: Qualquer célula com menos de dois vizinhos vivos morre de solidão.
                    if(numerodevizinhos < 2)
                    {
                        estadosaida = 0;
                    }
                    else
                    {
                        //Regra: Qualquer célula viva com dois ou três vizinhos continua no mesmo estado para a proxima geração.
                        if((numerodevizinhos == 2) || (numerodevizinhos == 3))
                        {
                            estadosaida = estadocelula;
                        }
                    }
                }
            }
        }
        else
        {
            //Primeira linha do quadro, linha superior.
            if((posx > 0) && (posy == 0))
            {
                if(estadocelula == 0)//(Célula morta)
                {
                    
                    if(posx == 29)
                    {
                        estadosaida = estadocelula;
                    }
                    else
                    {
                        numerodevizinhos = QtdeVizinhos2(posx-1, posx+1, posy, posx)+QtdeVizinhos3(posx-1, posx-1, posy+1);
                        //Regra: Qualquer célula morta com exatamente três vizinhos se torna uma célula viva.
                        if(numerodevizinhos == 3)
                        {
                            estadosaida = 1;
                        }
                      /*  else
                        {
                            //Qualquer outro estado a célula estará morta.
                            estadosaida = 0;
                        }       */
                    }
                }
                else
                {//Estado de celula igual 1(Célula viva)
                    if(posx == 29)
                    {
                        estadosaida = 1;
                    }        
                    else
                    {
                        numerodevizinhos = QtdeVizinhos2(posx-1, posx+1, posy, posx)+QtdeVizinhos3(posx-1, posx-1, posy+1);
                        //Regra: Qualquer célula com mais de três vizinhos vivos morre de superpopulação.
                        if(numerodevizinhos > 3)
                        {
                            estadosaida = 0;
                        }
                        else
                        {
                            //Regra: Qualquer célula com menos de dois vizinhos vivos morre de solidão.
                            if(numerodevizinhos < 2)
                            {
                                estadosaida = 0;
                            }
                            else
                            {   
                                //Regra: Qualquer célula viva com dois ou três vizinhos continua no mesmo estado para a proxima geração.
                                if((numerodevizinhos == 2) || (numerodevizinhos == 3))
                                {
                                    estadosaida = estadocelula;
                                }
                            }
                        }  
                    }
                }
            }
            else
            {
                //Primeira coluna a esquerda.
                if((posy > 0) && (posx == 0))
                {
                    //numerodevizinhos = QtdeVizinhos2(posx-1, posx+1, posy, posx);
                    estadosaida = 0;
                }else{
                //Primeira coluna a direita.
                //Ultima linha do quadro, linha inferior.
                //Linhas e colunas em geral.
                    if(estadocelula == 0)
                    {
                        estadosaida = 0;
                    }
                    else
                    {
                        estadosaida = 1;
                    }
                }
            }
        }
                
        return estadosaida;
    }//metodo verifica vizinhos da celula.
    
    int vizinhoCelula2(int posicaoinicial, int posicaoy, int posx, int posy)
    {
        int limite = 0;
        int contador = 0;
        int numerovizinhos = 0;
        if(posicaoinicial > 29)
        {
            limite = 28;
        }else
        {
            limite = posicaoinicial;
        }
        
        while(contador <=limite)
        {
            if(contador != posx)
            {
                if(MatrizAuxiliar[contador][posicaoy] == 1)
                {
                    numerovizinhos = numerovizinhos+1;
                }
            }
            contador = contador+1;
        }
        return numerovizinhos;
    }
    
    int vizinhoCelula3(int posicaoinicial, int posicaoy)
    {
        int limite = 0;
        int contador = 0;
        int numerovizinhos = 0;
        if(posicaoinicial > 30)
        {
            limite = 28;
        }else
        {
            limite = posicaoinicial+1;
        }        
        //limite = posicaoinicial+1;
        while(contador <=limite)
        {
           // if(contador != posx)
          //  {
                if(MatrizAuxiliar[contador][posicaoy] == 1)
                {
                    numerovizinhos = numerovizinhos+1;
                }
           // }
            contador = contador+1;
        }        
        return numerovizinhos;
    }
    
    //função que aborda a busca de vizinhos na mesma linha da posição atual.
    int QtdeVizinhos2(int posicaoinicial, int posicaofinal, int linhaatual, int posicaoexcecao)
    {
        int quantidadevizinhos = 0;
        int contador = 0;
        int limite = 0;
        
        limite = posicaofinal;
        contador = posicaoinicial;
        
        while(contador <= limite)
        {
            if(contador != posicaoexcecao)
            {
                if(MatrizAuxiliar[contador][linhaatual] == 1)
                {
                   quantidadevizinhos = quantidadevizinhos+1;
                }
            }
            contador = contador+1;
        }
        
        return quantidadevizinhos;
    }
    
    //função que verifica a parte de baixo e de cima da celula atual.
    int QtdeVizinhos3(int posicaoinicial, int posicaofinal, int linhaatual)
    {
        int quantidadevizinhos = 0;
        int contador = 0;
        int limite = 0;
       
        limite = posicaofinal;
        contador = posicaoinicial;
        
        while(contador<=limite)
        {
            if(MatrizAuxiliar[contador][linhaatual] == 1)
            {
                quantidadevizinhos = quantidadevizinhos+1;                
            }
            contador = contador+1;
        }
       
        return quantidadevizinhos;
    }
    
    //função aplicada na linha atual considerando a ultima posição
    int QtdeVizinhosExtX2(int posicaoanterior, int linhaatual)
    {
        int quantidadevizinhos = 0;
        if(MatrizAuxiliar[posicaoanterior][linhaatual]==1)
        {
            quantidadevizinhos = 1;
        }
        
        return quantidadevizinhos;
    }
    
    //função aplicada na linha atual considerando a ultima posição, e verificando a parte de baixo.
    int QtdeVizinhosExtX3(int posicaoinicial,int posicaofinal, int linhaatual)
    {
        int quantidadevizinhos = 0;
        int limite = 0;
        int contador = 0;
        
        limite = posicaofinal;
        contador = posicaoinicial;
        
        while(contador <= limite)
        {
            if(MatrizAuxiliar[contador][linhaatual] == 1)
            {
                quantidadevizinhos = quantidadevizinhos+1;
            }
            contador = contador+1;
        }
               
        return quantidadevizinhos;
    }
    
    
}

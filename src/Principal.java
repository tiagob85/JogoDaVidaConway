
import jogodavida.classes.MatrizJG;
import jogodavida.gui.DesenhaGrade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tiago B
 */
public class Principal 
{
    public static void main(String[] args) {
        System.out.println("Jogo da vida - Conway");
      //  DesenhaGrade obj = new DesenhaGrade();
       // obj.Desenha();
        MatrizJG obj = new MatrizJG();
        obj.ProcessaMatriz();
        System.out.println("----------------");
    //    obj.ImprimeMatrizCoord();
        
    }
}

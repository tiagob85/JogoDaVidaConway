/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavida.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tiago B
 */
public class DesenhaGrade  extends JPanel
{
    	int Matriz[][];
	JFrame frame = new JFrame();
        
	public DesenhaGrade(){}//fim do construtor.
	
	public void setMatriz(int[][] mtriz)
	{
                frame.setTitle("Jogo da vida - Conway");
		Matriz = mtriz;
	}//fim do metodo setMatriz
	
	public void paintComponent(Graphics g)
	{
		int aux = 20;
		int AuxiliarY = 0;
		int ContadorLinhaY = 20;
		int ContadorLinhaX = 10;
		int flag = 0;
		Random Flag = new Random();
				
		for(int contadorLinha = 0;contadorLinha<30;contadorLinha++)
		{	
			for(int contadorColuna = 0;contadorColuna<30;contadorColuna++)
			{
				if(Matriz[contadorLinha][contadorColuna] == 1)
				{
                                    g.setColor(Color.black);
				}
                                else
                                {
                                    g.setColor(Color.white);
                                }
				
				
				/*if(Flag.nextInt(2) == 1)
				{
					g.setColor(Color.black);
				}*/
					
				
				g.fillRect(10+(contadorColuna*aux),AuxiliarY,20,20);
			}
			
			for(int contadory = 0;contadory<10;contadory++)
			{
				g.setColor(Color.BLACK);
				g.drawLine(ContadorLinhaX,0,ContadorLinhaX,600 );
				
			}
			
			AuxiliarY = AuxiliarY+20;
		}	
		
		//Desenha linhas na matriz.
		for(int contadorx = 0;contadorx<30;contadorx++)
		{
			
			for(int contadory = 0;contadory<10;contadory++)
			{
				g.setColor(Color.BLACK);
				g.drawLine(ContadorLinhaX,0,ContadorLinhaX,600 );
				
			}
			ContadorLinhaX = ContadorLinhaX+20;
			g.setColor(Color.BLACK);
			g.drawLine(10,ContadorLinhaY,609,ContadorLinhaY );
			ContadorLinhaY = ContadorLinhaY+20;
		}
                repaint();
	}//fim do metodo paintComponent.
        
        
        public void Desenha()
        {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(this);
            frame.setSize(800, 600);
            frame.setVisible(true);
        }    
}

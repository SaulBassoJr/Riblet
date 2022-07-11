/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Saul
 */
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Scanner;

public class Riblet extends Frame {

    private static int tamanhoAmos;
    private static double alturaRib;
    private static double angulo;
    private static double distCentro;
    private static double distPrimeiro;
    
  {
    addWindowListener(new MyFinishWindow());
  }

    public Riblet(int tamanhoAmos, double alturaRib, double angulo, double distCentro, double distPrimeiro) {
        this.tamanhoAmos = tamanhoAmos;
        this.alturaRib = alturaRib;
        this.angulo = angulo;
        this.distCentro = distCentro;
        this.distPrimeiro = distPrimeiro;
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        GeneralPath gp = new GeneralPath();

        //Linha da Base
        gp.moveTo(30, 500);
        gp.lineTo(tamanhoAmos, 500);

        double lateral = alturaRib / Math.sin(angulo);
        double base = Math.abs(lateral) / 2;
        double centro;

        gp.moveTo(distPrimeiro, 500);
        gp.lineTo(gp.getCurrentPoint().getX() + base, 500);
        gp.lineTo(gp.getCurrentPoint().getX() - (base / 2), 500 - alturaRib);
        centro = (int) gp.getCurrentPoint().getX();
        gp.lineTo(gp.getCurrentPoint().getX() - (base / 2), gp.getCurrentPoint().getY() + alturaRib);

        for (int aux = 0; aux <= tamanhoAmos; aux++) {
            gp.moveTo(aux, 500);
            if (aux == (centro + distCentro)) {
                gp.moveTo(gp.getCurrentPoint().getX() - (base / 2), 500);
                gp.lineTo(gp.getCurrentPoint().getX() + base, 500);
                gp.lineTo(gp.getCurrentPoint().getX() - (base / 2), 500 - alturaRib);
                centro = (int) (gp.getCurrentPoint().getX());
                gp.lineTo(gp.getCurrentPoint().getX() - (base / 2), gp.getCurrentPoint().getY() + alturaRib);
            }
        }
        g2d.draw(gp);

        g2d.setColor(new Color(0, 0, 0));
        g2d.setStroke(new BasicStroke(1.0f));

        drawSimpleCoordinateSystem(1500, 1000, g2d);
    }

    public static void drawSimpleCoordinateSystem(int x, int y,
            Graphics2D g2d) {
        int xOffset = 30;
        int yOffset = 50;
        int step = 20;
        String s;
        Font fo = g2d.getFont();
        g2d.setFont(new Font("sansserif", Font.PLAIN, 9));
        g2d.drawLine(xOffset, yOffset, x, yOffset);
        for (int i = xOffset + step; i <= x; i = i + step) {
            g2d.drawLine(i, yOffset - 2, i, yOffset + 2);
            g2d.drawString(String.valueOf(i), i - 7, yOffset - 7);
        }

        g2d.drawLine(xOffset, yOffset, xOffset, y);

        s = "  ";
        for (int i = yOffset + step; i <= y; i = i + step) {
            g2d.drawLine(xOffset - 2, i, xOffset + 2, i);
            if (i > 99) {
                s = "";
            }
            g2d.drawString(s + String.valueOf(i), xOffset - 25, i + 5);
        }

        g2d.setFont(fo);
    }

    public static void main(String[] argv) {
        Scanner ler = new Scanner(System.in);

        System.out.println("Tamanho da amostra:");
        int tamanhoAmos = ler.nextInt();
        System.out.println("Altura do Riblet:");
        double alturaRib = ler.nextDouble();
        System.out.println("Angulo do topo do Riblet:");
        double angulo = ler.nextDouble();
        System.out.println("Distancia entre os centros dos Riblets:");
        double distCentro = ler.nextDouble();
        System.out.println("Distancia do início para o primeiro Riblet:");
        double distPrimeiro = ler.nextDouble();

        Riblet f = new Riblet(tamanhoAmos, alturaRib, angulo, distCentro, distPrimeiro);
        f.setTitle(" RIBLET ");
        f.setSize(800, 1400);
        f.setVisible(true);
    }
}
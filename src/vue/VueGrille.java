package vue;

import util.EcouteurModele;
import javax.swing.JPanel;
import modele.DirectionEnum;
import modele.GrilleModele;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;


public class VueGrille extends JPanel implements EcouteurModele, MouseListener, KeyListener{
   
    // Modèle de grille associé à la vue de la grille
   GrilleModele grille;

    // Coordonnée X de la souris au moment où elle a été pressée
   private int mousePressedX;

   // Coordonnée Y de la souris au moment où elle a été pressée
   private int mousePressedY;
   
   // Coordonnée X actuelle de la souris
   private int mousseX;
   // Coordonnée Y actuelle de la souris
   private int mousseY;
   public static final int dim = 3;
   public static final int nb_colors = 3;
   public static final int WIDTH = 700; 
   public static final int HEIGHT = 700; 
   // Nombre de coups effectués dans le jeu
   private int nbreCoups;
   
    /**
    * Constructeur de la classe VueGrille.
    * @param grille La grille de jeu de Taquin à afficher.
    */
   public VueGrille(GrilleModele grille){
       this.grille = grille;
       this.grille.ajoutEcouteur(this);
       addMouseMotionListener(new MouseMotionListener() {

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousseX = e.getX();
            mousseY = e.getY();
            repaint();
            int width = getWidth();
            int height = getHeight();
            int cote = Math.min(width / grille.getNbreColonne(), height / grille.getNbreLigne()) ;
            int x = e.getX();
            int y = e.getY();
            int caseX = x / cote;
            int caseY = y / cote;
            int caseVideX = grille.getPosXCaseVide();
            int caseVideY = grille.getPosYCaseVide();
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, -1, 0, 1};
            for (int i = 0; i < dx.length; i++){
                if (caseX == caseVideX + dx[i] && caseY == caseVideY + dy[i]){
                    String mot = Integer.toString(grille.getGrille()[caseX][caseY].getValeur());
                    int case2X = caseX * cote;
                    int case2Y = caseY * cote;
                    paintCase2(getGraphics(), case2X, case2Y, mot);
                }
            }
        }
        
       });
       

       addKeyListener(this);
       addMouseListener(this);
       
       setFocusable(true);
       setBackground(Color.DARK_GRAY);
       requestFocus();
       nbreCoups = 0;

       requestFocus();

}

   

        @Override
        public void keyPressed(KeyEvent k){
            
             if(k.getKeyCode() == KeyEvent.VK_Z  || k.getKeyCode() == KeyEvent.VK_UP){
                VueGrille.this.grille.bougerCaseVide(DirectionEnum.HAUT);

             } 
             else if(k.getKeyCode() == KeyEvent.VK_Q || k.getKeyCode() == KeyEvent.VK_LEFT){
                VueGrille.this.grille.bougerCaseVide(DirectionEnum.GAUCHE);
             }
             else if(k.getKeyCode() == KeyEvent.VK_S || k.getKeyCode() == KeyEvent.VK_RIGHT){
                VueGrille.this.grille.bougerCaseVide(DirectionEnum.DROITE);
             }
             else if(k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_DOWN){
                VueGrille.this.grille.bougerCaseVide(DirectionEnum.BAS);
             }
 
             repaint();
        }


 
      
        public void keyReleased(KeyEvent k) {
            
            
        }

        public void keyTyped(KeyEvent k) {
            
            
         }


    @Override
    public void mouseClicked(MouseEvent e) {
        int width = getWidth();
        int height = getHeight();
        int cote = Math.min(width / grille.getNbreColonne(), height / grille.getNbreLigne()) ;
        int x = e.getX();
        int y = e.getY();
        int caseX = x / cote;
        int caseY = y / cote;
        //System.out.println(e.getX() + " "  + e.getY());
        //System.out.println(caseX + " "  + caseY);
        grille.swap(caseX, caseY);
    }



    @Override
    public void mouseEntered(MouseEvent e) {
       

    }


    @Override
    public void mouseExited(MouseEvent e) {
       
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
        repaint();
        
    }


    @Override
    public void mouseReleased(MouseEvent e) {
      
        
    }


     /**
     * Définit le nombre de coups effectués.
     * @param nbreCoups Le nombre de coups effectués.
     */
    public void setNbreCoups(int nbreCoups){
        this.nbreCoups = nbreCoups;
        repaint();
    }

     /**
     * Affiche le nombre de coups effectués.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    private void afficherNbreCoups(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        int x = getWidth() - 200; // 100 est une valeur arbitraire pour décaler le texte de la marge droite
        int y = getHeight() - 10;
        g.drawString("Nombre de coups: " + nbreCoups, x, y);
    }






    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
        nbreCoups++;
    }
     /**
     * Redessine la grille de jeu avec les composants mis à jour.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    public void paintComponent(Graphics	g){
        super.paintComponent(g);
        int width = getWidth();	
        int height = getHeight();
        int cote = Math.min(width / grille.getNbreColonne(), height / grille.getNbreLigne()) ;
        int startX = 0;
        int startY = 0;
        g.setColor(Color.RED);

        if(grille != null){
        for (int lig = 0; lig < dim; lig++){
            for (int col = 0; col < dim; col++){
                int x = startX + lig * cote;
                int y = startY + col * cote;
                String mot = "";
                if(grille.getGrille()[lig][col].getValeur() != 0 && grille.getGrille()[lig][col] != null){
                    mot = Integer.toString(grille.getGrille()[lig][col].getValeur());
                }
                paintCase(g, x, y, mot);
            }
        }
    } 
    afficherNbreCoups(g);

    }

     /**
     * Dessine une case de la grille.
     * @param gr L'objet Graphics utilisé pour dessiner.
     * @param x La coordonnée X de la case.
     * @param y La coordonnée Y de la case.
     * @param mot Le texte à afficher dans la case.
     */
    void paintCase(Graphics gr, int x, int y, String mot){
        int cote = Math.min(getWidth(), getHeight()) / dim;
        int fontSize = cote/2;
        Font font = new Font("Arial", Font.BOLD, fontSize);
        gr.setColor(Color.darkGray);
        gr.fillRect(x, y, cote, cote);
        gr.setColor(Color.PINK);
        gr.drawRect(x, y, cote, cote);

        if(mot.isEmpty()){
            gr.setColor(Color.WHITE);
            gr.fillRect(x, y, cote, cote);
        } else {
            gr.setColor(Color.GRAY);
            gr.drawRect(x, y, cote, cote);
        }

        FontMetrics fm = gr.getFontMetrics();
        int textWidth = fm.stringWidth(mot);
        int textHeight = fm.getHeight();

        int centreX = x + (cote - textWidth) / 2;
        int centreY = y + (cote - textHeight) / 2 + fm.getAscent();
        gr.drawString(mot, centreX, centreY);
    }

     /**
     * Dessine une case de la grille en surbrillance.
     * @param gr L'objet Graphics utilisé pour dessiner.
     * @param x La coordonnée X de la case.
     * @param y La coordonnée Y de la case.
     * @param mot Le texte à afficher dans la case.
     */
    void paintCase2(Graphics gr, int x, int y, String mot){
        int cote = Math.min(getWidth(), getHeight()) / dim;
        gr.setColor(Color.GREEN);
        gr.fillRect(x, y, cote, cote);
        gr.setColor(Color.RED);
        gr.drawRect(x, y, cote, cote);

        FontMetrics fm = gr.getFontMetrics();
        int textWidth = fm.stringWidth(mot);
        int textHeight = fm.getHeight();

        int centreX = x + (cote - textWidth) / 2;
        int centreY = y + (cote - textHeight) / 2 + fm.getAscent();
        gr.drawString(mot, centreX, centreY);
    }

    /**
     * Met à jour la grille de jeu.
     * @param nouvelleGrille La nouvelle grille de jeu.
     */
    public void setGrille(GrilleModele nouvelleGrille) {
        this.grille = nouvelleGrille;
        this.repaint(); // Redessiner la grille avec la nouvelle grille
    }


}
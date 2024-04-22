package vue;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Screen extends JPanel{
    public DiviserImage div;
    public Screen(DiviserImage div){
        this.div = div;
    }

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);
        Graphics2D g = (Graphics2D) arg0;
        div.division();
    }
    
}

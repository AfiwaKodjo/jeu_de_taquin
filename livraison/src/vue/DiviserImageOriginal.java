package vue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.*;
import java.net.URL;

/**
 * La classe DiviserImageOriginal permet de diviser une image en sous-images de manière égale.
 */
public class DiviserImageOriginal {
    public static void main(String[] args) throws IOException {
        
        URL url = new URL("https://www.educative.io/api/edpresso/shot/5120209133764608/image/5075298506244096/test.jpg");

        InputStream is = url.openStream();
        BufferedImage image = ImageIO.read(is);

        // Nombre de lignes et de colonnes pour diviser l'image
        int rows = 4;
        int columns = 4;

         // Tableau pour contenir les sous-images
        BufferedImage imgs[] = new BufferedImage[16];

         // Division égale de l'image originale en sous-images
        int subimage_Width = image.getWidth() / columns;
        int subimage_Height = image.getHeight() / rows;
        
        int current_img = 0;
        
       // Parcours des lignes et des colonnes pour chaque sous-image
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                // Création de la sous-image
                imgs[current_img] = new BufferedImage(subimage_Width, subimage_Height, image.getType());
                Graphics2D img_creator = imgs[current_img].createGraphics();

               // Coordonnées de l'image source
                int src_first_x = subimage_Width * j;
                int src_first_y = subimage_Height * i;

                // Coordonnées de la sous-image
                int dst_corner_x = subimage_Width * j + subimage_Width;
                int dst_corner_y = subimage_Height * i + subimage_Height;
                
                img_creator.drawImage(image, 0, 0, subimage_Width, subimage_Height, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
                current_img++;
            }
        }

         // Dessin de la sous-image
        for (int i = 0; i < 16; i++)
        {
            File outputFile = new File("img" + i + ".jpg");
            ImageIO.write(imgs[i], "jpg", outputFile);
        }
        System.out.println("Sub-images have been created.");
    }
}

package Main;

import java.awt.*;

public class Button {

    private int xPadding = 0;
    ButtonClick clicker;
    private int width, height,topX,topY;
    private Color colour;
    private String buttonText;
    Font font = new Font("Arial", Font.PLAIN, 40);

    public Button(ButtonClick clicker, int width, int height, int topX, int topY, Color colour, String buttonText){
        this.width = width;
        this.height = height;
        this.clicker = clicker;
        this.topX = topX;
        this.topY = topY;
        this.colour = colour;
        this.buttonText = buttonText;
    }

    public Button(ButtonClick clicker, int width, int height, int topX, int topY,int xPadding, Color colour, String buttonText){
        this.width = width;
        this.height = height;
        this.clicker = clicker;
        this.topX = topX;
        this.topY = topY;
        this.xPadding = xPadding;
        this.colour = colour;
        this.buttonText = buttonText;
    }

    public void click() {
        clicker.click();
    }

    public void draw(Graphics2D g){
        g.setColor(colour);
        g.fillRect(topX,topY,width,height);
        g.setColor(new Color(80, 126, 126));
        g.setStroke(new BasicStroke(3));
        g.drawRect(topX,topY,width,height);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(buttonText,topX+40+xPadding,topY+60);
    }



    public void clickAt(int x, int y){
        if(x >= topX && x <= (topX+width) && y >= topY && y <= (topY + height)){
            click();
        }
    }

    public boolean mouseOver(int x, int y){
        return (x >= topX && x <= (topX+width) && y >= topY && y <= (topY + height));
    }

}

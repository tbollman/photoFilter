import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {
    private JFrame frame;
    private JLabel imageLabel;
    private BufferedImage image = null;
    private String filter = "";
    private String originalFileName;
    private String originalPath;

    public App() {
        frame = new JFrame("Photo Filter");
        frame.setSize(400,400);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu optionMenu = new JMenu("Option");

        JMenuItem open = new JMenuItem("Open File");
        open.setActionCommand("Open");
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                imageLabel = new JLabel();
                frame.add(imageLabel);
                imageLabel.setIcon(new javax.swing.ImageIcon(displayImage()));
                frame.pack();
                frame.repaint();
            }
        });

        JMenuItem save = new JMenuItem("Save");
        save.setActionCommand("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (image != null) {
                    try {
                        File outputFile = new File(originalPath + filter + ".jpg");
                        ImageIO.write(image, "jpg", outputFile);
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(exit);

        JMenuItem greyScale = new JMenuItem("Grey Scale");
        greyScale.setActionCommand("Grey Scale");
        greyScale.addActionListener(event -> {
            System.out.println("test3");
            if (image != null) {
                toGreyScale();
                imageLabel.setIcon(new ImageIcon(image));
                frame.pack();
                frame.repaint();
                filter = "grey";
            }
        });

        JMenuItem sepiaTone = new JMenuItem("Sepia Tone");
        sepiaTone.setActionCommand("Sepia Tone");
        sepiaTone.addActionListener(event -> {
            System.out.println("test2");
            if (image != null) {
                toSepia();
                System.out.println("test");
                imageLabel.setIcon(new ImageIcon(image));
                frame.pack();
                frame.repaint();
                filter = "sepia";
            }
        });

        JMenuItem invert = new JMenuItem("Invert");
        invert.setActionCommand("Invert");
        invert.addActionListener(event -> {
            System.out.println("test2");
            if (image != null) {
                invert();
                System.out.println("test");
                imageLabel.setIcon(new ImageIcon(image));
                frame.pack();
                frame.repaint();
                filter = "sepia";
            }
        });


        optionMenu.add(greyScale);
        optionMenu.add(sepiaTone);
        optionMenu.add(invert);

        menuBar.add(fileMenu);
        menuBar.add(optionMenu);

        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Image displayImage() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(chooser);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                image = ImageIO.read(chooser.getSelectedFile());
                String temp = chooser.getSelectedFile().getAbsolutePath();
                originalPath = temp.substring(0,temp.length()-4);

                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void toGreyScale() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Pixel pixel = new Pixel(image.getRGB(j,i));
                pixel.setGrey();
                image.setRGB(j, i, pixel.getRGB());
            }
        }
    }

    public void toSepia() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Pixel pixel = new Pixel(image.getRGB(j,i));
                pixel.setSepia();
                image.setRGB(j, i, pixel.getRGB());
            }
        }
    }

    public void invert() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Pixel pixel = new Pixel(image.getRGB(j,i));
                pixel.setInvert();
                image.setRGB(j, i, pixel.getRGB());
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}

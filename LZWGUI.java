import LZW.LZW_Implementation;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LZWGUI extends JFrame implements ActionListener {

    private JLabel lblCompression;
    private JLabel lblDecompress;
    private JPanel pnlMain;
    private JPanel pnlSub;
    private JFrame frmMain;
    private JLabel lblHeader;
    private JButton btnClose;
    private JButton btnCompress;
    private JButton btnDecompress;
    private JRadioButton radWord;
    private JRadioButton radImg;
    private JRadioButton radAudio;
    private JRadioButton radDLL;
    private JRadioButton radExe;

    public LZWGUI() {
        prepareGUI();
    }

    public void prepareGUI() {
        frmMain = new JFrame("LZW Implementation");
        frmMain.setSize(400, 400);
        frmMain.setLayout(new GridLayout(2, 1));
        frmMain.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        pnlMain = new JPanel();
        pnlMain.setLayout(new GridLayout(6, 1));
        pnlMain.setSize(400, 250);
        frmMain.add(pnlMain);

        pnlSub = new JPanel();
        pnlSub.setLayout(new FlowLayout());
        pnlSub.setSize(400, 150);

        frmMain.add(pnlSub);

        lblHeader = new JLabel("Choose File Type: ", JLabel.LEFT);
        pnlMain.add(lblHeader);

        radWord = new JRadioButton("Word File", true);
        radImg = new JRadioButton("Image File");
        radAudio = new JRadioButton("Audio File");
        radDLL = new JRadioButton("DLL File");
        radExe = new JRadioButton("Executable File");

        ButtonGroup group = new ButtonGroup();
        group.add(radWord);
        group.add(radImg);
        group.add(radAudio);
        group.add(radDLL);
        group.add(radExe);

        pnlMain.add(radWord);
        pnlMain.add(radImg);
        pnlMain.add(radAudio);
        pnlMain.add(radDLL);
        pnlMain.add(radExe);

        btnClose = new JButton("Close");
        btnCompress = new JButton("Compress File");
        btnDecompress = new JButton("Decompress File");

        btnClose.setSize(50, 60);
        btnCompress.setSize(50, 60);
        btnDecompress.setSize(50, 60);
        btnClose.addActionListener(this);
        btnCompress.addActionListener(this);
        btnDecompress.addActionListener(this);

        pnlSub.add(btnCompress);
        pnlSub.add(btnDecompress);
        pnlSub.add(btnClose);

        frmMain.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        FileInputStream fsInp;
        FileOutputStream fsOut;
        LZW_Implementation lzw = new LZW_Implementation();

        if (ae.getSource() == btnClose) {
            System.exit(0);
        } else if (ae.getSource() == btnCompress) {
            try {
                if (radWord.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Sample_Word.doc"));
                    fsOut = new FileOutputStream(new File(".\\Files\\Sample_Word.doc.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Word file is compressed.");
                } else if (radImg.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Chart.jpg"));
                    fsOut = new FileOutputStream(new File(".\\Files\\Chart.jpg.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Image File is compressed.");
                } else if (radAudio.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\QuitPlayingGames.mp3"));
                    fsOut = new FileOutputStream(new File(".\\Files\\QuitPlayingGames.mp3.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Audio File is compressed.");
                } else if (radExe.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Sample.exe"));
                    fsOut = new FileOutputStream(new File(".\\Files\\Sample.exe.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Executable File is compressed.");
                } else if (radDLL.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Sample.dll"));
                    fsOut = new FileOutputStream(new File(".\\Files\\Sample.dll.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "DLL file is compressed.");
                }
            } catch (FileNotFoundException fe) {
                JOptionPane.showMessageDialog(null, "File is not found.");
            } catch (IOException ie) {
            }
        } else if (ae.getSource() == btnDecompress) {
            try {
                if (radWord.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Sample_Word.doc.lzw"));
                    fsOut = new FileOutputStream(new File(".\\Files\\Sample_WordNew.doc"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Word file is decompressed");
                } else if (radImg.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Chart.jpg.lzw"));
                    fsOut = new FileOutputStream(new File(".\\Files\\ChartNew.jpg"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Image file is decompressed");
                } else if (radAudio.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\QuitPlayingGames.mp3.lzw"));
                    fsOut = new FileOutputStream(new File(".\\Files\\QuitPlayingGamesNew.mp3"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Audio file is decompressed");
                } else if (radExe.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Sample.exe.lzw"));
                    fsOut = new FileOutputStream(new File(".\\Files\\SampleNew.exe"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Executable file is decompressed");
                } else if (radDLL.isSelected()) {
                    fsInp = new FileInputStream(new File(".\\Files\\Sample.dll.lzw"));
                    fsOut = new FileOutputStream(new File(".\\Files\\SampleNew.dll"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "DLL file is decompressed");
                }
            } catch (FileNotFoundException fe) {
                JOptionPane.showMessageDialog(null, "Compression is not done. Please perform compression for file.");
            } catch (IOException ie) {
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        LZWGUI lzw = new LZWGUI();
        /*
         Scanner keyboard = new Scanner(System.in);
         int maininputno;
         FileInputStream fsInp;
         FileOutputStream fsOut;
         LZW lzw = new LZW();

         do {
         System.out.println("===== Choose Option for LZW Compression - Decompression ======");
         System.out.println("1. LZW Compression");
         System.out.println("2. LZW Decompression");
         System.out.println("3. Exit");
         maininputno = keyboard.nextInt();

         if (maininputno == 3) {
         System.out.println("Program terminated.");
         System.exit(0);
         } else if (maininputno == 1) {
         int selectionno;
         do {
         System.out.println("===== Choose File for LZW Compression ======");
         System.out.println("1. Word Document");
         System.out.println("2. DLL File");
         System.out.println("3. Audio File");
         System.out.println("4. Image File");
         System.out.println("5. Executable File");
         System.out.println("6. Return to main menu");
         selectionno = keyboard.nextInt();
         try {
         switch (selectionno) {
         case 1:
         fsInp = new FileInputStream(new File(".\\Files\\darknet5.doc"));
         fsOut = new FileOutputStream(new File(".\\Files\\darknet5.doc.lzw"));
         lzw.compress(fsInp, fsOut);
         break;
         case 6:
         break;
         }
         } catch (FileNotFoundException fe) {
         }
         } while (selectionno != 6);
         } else if (maininputno == 2) {
         int selectionno;
         do {
         System.out.println("===== Choose File for LZW Decompression ======");
         System.out.println("1. Word Document");
         System.out.println("2. DLL File");
         System.out.println("3. Audio File");
         System.out.println("4. Image File");
         System.out.println("5. Executable File");
         System.out.println("6. Return to main menu");

         selectionno = keyboard.nextInt();

         switch (selectionno) {
         case 1:
         fsInp = new FileInputStream(new File(".\\Files\\darknet5.doc.lzw"));
         fsOut = new FileOutputStream(new File(".\\Files\\darknet5New.doc"));
         lzw.decompress(fsInp, fsOut);
         case 6:
         break;
         }
         } while (selectionno != 6);
         }
         } while (true);
         */
    }
}

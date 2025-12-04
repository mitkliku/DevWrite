import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Texteditor implements ActionListener {
     JFrame Jframe;
     JMenuBar menuBar;
     JMenu file, edit;
     JMenuItem newfile,openfile,savefile;

     JMenuItem copy,paste,cut,close,selectall;
     JTextArea textArea;

    Texteditor(){
      Jframe = new JFrame();
      menuBar = new JMenuBar();
      textArea = new JTextArea();
      file = new JMenu("File");
      edit = new JMenu("Edit");
      newfile=new JMenuItem("Start Fresh");
      openfile = new JMenuItem("Open your file");
      savefile = new JMenuItem("Secure your file");

      newfile.addActionListener(this);
      openfile.addActionListener(this);
      savefile.addActionListener(this);
      file.add(newfile);
      file.add(openfile);
      file.add(savefile);

      copy = new JMenuItem("Copy");
      paste = new JMenuItem("paste");
      cut = new JMenuItem("cut");
      selectall = new JMenuItem("selectall");
      close = new JMenuItem("close");

      copy.addActionListener(this);
      paste.addActionListener(this);
      cut.addActionListener(this);
      selectall.addActionListener(this);
      close.addActionListener(this);

      edit.add(copy);
      edit.add(paste);
      edit.add(cut);
      edit.add(selectall);
      edit.add(close);

      menuBar.add(file);
      menuBar.add(edit);

      Jframe.setJMenuBar(menuBar);
      JPanel jPanel = new JPanel();
      jPanel.setBorder(new EmptyBorder(5,5,5,5));
      jPanel.setLayout(new BorderLayout(0,0));

      //jPanel.add(textArea,BorderLayout.CENTER);
      JScrollPane jScrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jPanel.add(jScrollPane,BorderLayout.CENTER);
      Jframe.add(jPanel);

      //Jframe.add(textArea);
      Jframe.setBounds(100,100,400,400);
      Jframe.setTitle("DevWrite");
      Jframe.setVisible(true);
        Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Jframe.setLayout(null);
    }

    @Override
   public void actionPerformed(ActionEvent actionEvent){
     if(actionEvent.getSource()==cut){
         textArea.cut();
     }
     if(actionEvent.getSource()==copy){
         textArea.copy();
     }
     if(actionEvent.getSource()==paste){
         textArea.paste();;
     }
     if(actionEvent.getSource()==selectall){
         textArea.selectAll();
     }
     if(actionEvent.getSource()==close){
         System.exit(0);
     }
     if(actionEvent.getSource()==openfile){
         JFileChooser filechooser = new JFileChooser("C:");
         int chooseOption = filechooser.showOpenDialog(null);

         if(chooseOption == JFileChooser.APPROVE_OPTION){
             File file = filechooser.getSelectedFile();
             String filepath = file.getPath();

             try{
                 FileReader fileReader = new FileReader(filepath);
                 BufferedReader bufferedReader = new BufferedReader(fileReader);

                 String intermediate = "" ,output = "";
                 while((intermediate=bufferedReader.readLine())!=null){
                     output+=intermediate+"\n";
                 }
              textArea.setText(output);
             } catch (IOException ioException){
                 ioException.printStackTrace();
             }

         }
     }

         if (actionEvent.getSource() == savefile) {

             JFileChooser fileChooser = new JFileChooser("C:");
             int choose = fileChooser.showSaveDialog(null);

             if (choose == JFileChooser.APPROVE_OPTION) {
                 File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                 try {
                     BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                     textArea.write(writer);
                     writer.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }

         if(actionEvent.getSource()==newfile){
             Texteditor texteditor = new Texteditor();
         }

    }
    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
        //System.out.println("Hello, World!");
    }
}
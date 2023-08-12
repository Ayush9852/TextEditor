import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of TextEditor
    JFrame frame;
    //Initialize a menuBar
    JMenuBar menuBar;
    JMenu file, edit;

    //File menu items
    JMenuItem newFile, openFile, saveFile;
    //Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    //text area
    JTextArea textArea;
    TextEditor(){
        //Initializing a frame
        frame = new JFrame();

        //Initializing a menubar
        menuBar = new JMenuBar();

        //Initialize text area
        textArea = new JTextArea();
        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu items
        cut =  new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //set menu bar to frame
        frame.setJMenuBar(menuBar);
        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        // set dimensions of frame
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor(by Ayush)");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //perform copy operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //perform close editor operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    //initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize the buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                } catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Initialize a file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //choose if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //Create a new file with chosen directory path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {
                    //Initialize  file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffered reader
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of the text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }

    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

    }
}
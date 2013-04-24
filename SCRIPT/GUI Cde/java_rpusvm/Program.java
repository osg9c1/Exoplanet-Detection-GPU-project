import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
	
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException; 
import java.io.InputStreamReader;

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
class GUI extends JFrame implements ActionListener {
	String path1=null,path=null;
	JTextField fileName;
	JButton browse, submit,about,help;

	BufferedImage img = null ;

	int pos=0,neg=0,un=0;
	GUI() {
		this(600, 600);
	}

	GUI(int width, int height) {
		this.setSize(width, height);
		this.setTitle("Detection of Exo-Planet");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.getContentPane().add(new Component("sample.jpeg"));
		try {
			img = ImageIO.read(new File("/home/regulus/Downloads/ExoPlanet02.jpg"));
		} catch (IOException e) {}
		this.setContentPane(new ImagePanel(img));
		createUI();
	}

	public void actionPerformed(ActionEvent e) {
		
		JFileChooser chooser = new JFileChooser();
		String data[][]=null;
		
		
		if (e.getSource() == browse) {

			chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		  
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    //
		    // disable the "All files" option.
		    //
		    chooser.setAcceptAllFileFilterUsed(false);
		    //    
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		    path = chooser.getSelectedFile().toString();
		    fileName.setText(path);
		      }
		    else {
		      System.out.println("No Selection ");
		      }}
		if(e.getSource() == about) {
			JOptionPane.showMessageDialog(null, "This project was created in partial fulfilment of the requirements of the course Operating Systems\nProject Advisor : Prof. Shrisha Rao\nTeam : Krishna Prem\nMridul Agarwal\nSneha Deshpande\nSujata Mehta\nSurabhi Pandey\nThis project is purely for academic use only.\nCredits : Uses RPUSVM library copyrighted by Chi-Yau, 2011-13");
		}
		if(e.getSource() == help) {
			JOptionPane.showMessageDialog(null, "The project is designed to take a database of candidate object data and output their classification.\nBrowse button : It allows user to select the directory which consists of test files.\nPredict Button : Predicts this testing data against trained data and displays output.");			
		}
	
		if (e.getSource() == submit) {
			long startTime = System.currentTimeMillis();
			boolean flag = true;
			if (flag) {
				
				try {
				
					    
					String temp1="./shellnew.sh"+ " " + path;
					//
					JOptionPane.showMessageDialog(null, "Processing...");
					
					//String cmd = "ls -al";
					Runtime run = Runtime.getRuntime();
					Process pr = run.exec(temp1);
					//pr.waitFor();
					BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
					String line = "";
					while ((line=buf.readLine())!=null) {
					System.out.println(line);
					}
				//	Runtime.getRuntime().exec("uname -a");
					//Runtime.getRuntime().exec(temp1);	
                                        
					refresh();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally{
                            //     pbw.end();
                                   }

				List<String> results = new ArrayList<String>();
				File[] files = new File("/home/regulus/workspace/OSproj/outputs/").listFiles();
				int row1=0,row2=0,row3=0;
				int ret;
				int rows=files.length;
				data=new String[rows][3];	

				for (File file : files) {
				    if (file.isFile()) {	    	
				    	try {
							ret=this.readFile(file.getPath(),file.getName());
							if(ret==1)
								data[row2++][1]=file.getName();
							else if(ret==-1)
								data[row3++][2]=file.getName();
							else
							data[row1++][0]=file.getName();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	        
				    }
					
				  }

				try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/final/output.txt",true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("\nTotal number of confirmed exoplanets : "+pos);
					out.write("\nTotal number of false planets : "+neg);
					out.write("\nTotal number of planets which are candidate and not confirmed: "+un);
					out.close();
				}catch(IOException eio){}
				try{
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/lists/dummy.csv",true);
					BufferedWriter out1 = new BufferedWriter(fstream);
					out1.write("Confirm,NASA,7\n");
					out1.write("Confirm,SVM,"+pos+"\n");
					out1.write("False Positive,NASA,1\n");
					out1.write("False Positive,SVM,"+neg+"\n");
					out1.write("Candidate,NASA,8\n");
					out1.write("Candidate,SVM,"+un+"\n");
					out1.close();
				}catch(IOException eio){}
				System.out.println("\nTotal number of confirmed exoplanets : "+pos);
				System.out.println("\nTotal number of false planets : "+neg);
				System.out.println("\nTotal number of planets which are candidate and not confirmed: "+un);
			}
			try{
				String temp2="./shelllast.sh";
				Runtime run = Runtime.getRuntime();
				Process pr2 = run.exec(temp2);
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr2.getInputStream()));
				String line = "";
				while ((line=buf.readLine())!=null) {
				System.out.println(line);
				}
			//	Runtime.getRuntime().exec("uname -a");
				//Runtime.getRuntime().exec(temp1);	
                                    
				refresh();
			}catch(Exception enew){}
			/*try{
				String temp3="./shellfinal.sh";
				Runtime run = Runtime.getRuntime();
				Process pr3 = run.exec(temp3);
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr3.getInputStream()));
				String line = "";
				while ((line=buf.readLine())!=null) {
				System.out.println(line);
				}
			//	Runtime.getRuntime().exec("uname -a");
				//Runtime.getRuntime().exec(temp1);	
                                    
				refresh();x
			}catch(Exception enew){}*/
			
			JTableExample mainFrame	= new JTableExample(data);
			mainFrame.setVisible( true );
			
			if (Desktop.isDesktopSupported()) {
			    try {
			        File myFile = new File("/home/regulus/workspace/OSproj/lists/Rplots.pdf");
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    }
			}
			if (Desktop.isDesktopSupported()) {
			    try {
			        File myFile = new File("/home/regulus/workspace/OSproj/dist/examples/new_2.html");
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    }
			}
	        long endTime = System.currentTimeMillis();
	        System.out.println("Time taken is : "+((endTime - startTime)/60000	) +" minutes" + ((endTime - startTime)/60000)%1000 + " seconds");
			
		}
		 // case 1


		

	}
	@SuppressWarnings("unused")
	private int readFile(String path2,String fname) throws IOException {
		
		Scanner sc = new Scanner(new File(path2));
		double count=0,total=0;
		int ret=0;
		while(sc.hasNextLine()){
			total++;
			String s=sc.nextLine();
		    if(Integer.parseInt(s.trim())==1)
		    	 count++;
		      
		    
		}
		 if(((count/total)*100+20) >=80 )
		 {
			  System.out.println("\nFile name "+ fname +" " +
			  		"contains data for confirmed planet");
			  try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/final/output.txt",true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("\nFile name "+ fname +" " +
					  		"contains data for confirmed planet   "+ (count/total)*100);
					out.close();
					ret=1;
					
				}catch(IOException eio){}
			  try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/lists/confirmed.txt",true);
					BufferedWriter out1 = new BufferedWriter(fstream);
					out1.write("\n"+ fname);
					out1.close();
					
				}catch(IOException eio){}
			  pos++;
		 }
		 else if(((count/total)*100+40) < 50)
		 {
			 System.out.println("\nFile name "+ fname +" " +
				  		"contains data for false planet");
			  try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/final/output.txt",true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("\nFile name "+ fname +" " +
					  		"contains data for false planet  "+(count/total)*100);
					ret=-1;
					out.close();
					
					//data[row++][2]=fname;
				}catch(IOException eio){}
			  try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/lists/false.txt",true);
					BufferedWriter out1 = new BufferedWriter(fstream);
					out1.write("\n"+ fname);
					out1.close();
				}catch(IOException eio){}
			 neg++;
		 }
		 else
		 {
			 System.out.println("\nCant say anything about the object with file name "+fname);
			  try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/final/output.txt",true);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("\nFile name "+ fname +" " +
					  		"contains data for candidate planet   "+(count/total)*100);
					//data[row++][0]=fname;
					ret=0;
					out.close();
					
				}catch(IOException eio){}
			  try{
					
					FileWriter fstream = new FileWriter("/home/regulus/workspace/OSproj/lists/candidate.txt",true);
					BufferedWriter out1 = new BufferedWriter(fstream);
					out1.write("\n"+ fname);
					out1.close();
				}catch(IOException eio){}
			 un++;
		 }
		 return ret;
	}
				


	void refresh() {
		fileName.setText("");

	}
	
	void createUI() {
		
		this.setLayout(null);
		fileName = new JTextField();
		
		JLabel file = new JLabel("File");
		file.setForeground(Color.white);
		Font fnt = new Font("Serit",Font.BOLD,18);
		file.setFont(fnt);
		browse = new JButton("Browse");
		submit = new JButton("Predict");
		about = new JButton("About");
		help = new JButton("Help");
		file.setLocation(50, 50); // x and y
		fileName.setLocation(100, 50);
		browse.setLocation(450, 50);
		submit.setLocation(230, 150);
		about.setLocation(360, 150);
		help.setLocation(100, 150);

		file.setSize(100, 50); // width and height
		fileName.setSize(300, 30);
		browse.setSize(120, 30);
		submit.setSize(120, 30);
		about.setSize(120, 30);
		help.setSize(120, 30);

		browse.addActionListener(this);
		submit.addActionListener(this);
		about.addActionListener(this);
		help.addActionListener(this);
		
		this.add(file);
		this.add(fileName);
		this.add(browse);
		this.add(submit);
		this.add(about);
		this.add(help);

	}


}


class UserInteraction {
	void start() {
		GUI ui = new GUI();
		ui.setVisible(true);

	}
}

class Program {
	public static void main(String[] args) {
		UserInteraction u = new UserInteraction();
		u.start();
		

	}
}
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import java.io.*;
	import java.awt.print.*;
	import java.util.*;
	public  class Notepad extends JFrame implements ActionListener,WindowListener
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2877285696950002901L;
		int f = -1;
		int gposcut = 0;
		String deltree="";
		String text="";
		
		PrinterJob printme = PrinterJob.getPrinterJob();
		
		MenuBar mb = new MenuBar();
		
		Menu file = new Menu("File");
		Menu edit = new Menu("Edit");
		Menu search = new Menu("Search");
		Menu help = new Menu("Help");
		
		MenuItem news = new MenuItem("New");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem saveas = new MenuItem("Save As");
		MenuItem pgset = new MenuItem("Page Setup");
		MenuItem print = new MenuItem("Print");
		MenuItem exit = new MenuItem("Exit");
		
		MenuItem undo = new MenuItem("Undo");
		MenuItem cut = new MenuItem("Cut");
		MenuItem copy = new MenuItem("Copy");
		MenuItem paste = new MenuItem("Paste");
		MenuItem delete = new MenuItem("Delete");
		MenuItem selectall = new MenuItem("Select All");
		MenuItem td = new MenuItem("Time/Date");
		MenuItem font = new MenuItem("Set Font");
		CheckboxMenuItem wordrap = new CheckboxMenuItem("Word Rap");
		
		MenuItem find = new MenuItem("Find...");
		MenuItem findnext = new MenuItem("Find Next");
		
		MenuItem about = new MenuItem("About");
		MenuItem feat = new MenuItem("Features");
		
		String flag = "n";
		
		JPanel p1 = new JPanel();
		
		TextArea area = new TextArea(5,4);
		Font ver = new Font("Verdana",0,14);
		int gposdel=0;
		int gpos = 0;
		int spos = 0;
		
		String searchall;
		
		String filee = "";
		String directory = "";
		
		Date d = new Date();
		GregorianCalendar g = new GregorianCalendar();
		String stime;
		int hour=0;
		
		public void date()
		{
			hour = g.get(Calendar.HOUR);
			if (hour == 0)
			{
				hour = 12;
			}
			
			g.setTime(d);
			
			if ( g.get(Calendar.AM_PM) == 0 )
			{
				stime = hour + ":" + g.get(Calendar.MINUTE) + " AM " + (g.get(Calendar.MONTH)+1) + "/" + g.get(Calendar.DATE) + "/" + g.get(Calendar.YEAR);
			}
			if ( g.get(Calendar.AM_PM) == 1 )
			{
				stime = hour + ":" + g.get(Calendar.MINUTE) + " PM " + (g.get(Calendar.MONTH)+1) + "/" + g.get(Calendar.DATE) + "/" + g.get(Calendar.YEAR);
			}
			System.out.println("The Date/Time Is : " + stime);
			
		}
		
		public Notepad()
		{
			setTitle("Notepad Clone");
			setSize(600,400);
			setVisible(true);
			
			date();
			
			file.add(news);
			file.add(open);
			file.add(save);
			file.add(saveas);
			file.addSeparator();
			file.add(pgset);
			file.add(print);
			file.addSeparator();
			file.add(exit);
			news.addActionListener(this);
			open.addActionListener(this);
			save.addActionListener(this);
			saveas.addActionListener(this);
			pgset.addActionListener(this);
			print.addActionListener(this);
			exit.addActionListener(this);

			edit.add(undo);
		//	edit.addSeparator();
			edit.add(cut);
			edit.add(copy);
			edit.add(paste);
			edit.add(delete);
		//	edit.addSeparator();
			edit.add(selectall);
			edit.add(td);
		//	edit.addSeparator();
			edit.add(wordrap);
			edit.add(font);
			undo.addActionListener(this);
			cut.addActionListener(this);
			copy.addActionListener(this);
			paste.addActionListener(this);
			delete.addActionListener(this);
			selectall.addActionListener(this);
			td.addActionListener(this);
			wordrap.addActionListener(this);
			/*wordrap.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});*/
			search.add(find);
			search.add(findnext);
			find.addActionListener(this);
			findnext.addActionListener(this);
		
			help.add(feat);
			//help.addSeparator();
			help.add(about);
			about.addActionListener(this);
			feat.addActionListener(this);
			
			mb.add(file);
			mb.add(edit);
			mb.add(search);
			mb.add(help);
			
			
			setMenuBar(mb);
			
			area.setFont(ver);
			
			Container c = getContentPane();
			c.add(area);
			
			addWindowListener(this);
		}
		
		public void windowClosing(WindowEvent we)
		{
				System.out.println("Thank You For Using ChandraPAD");
				System.exit(0);
		}

		public void windowIconified(WindowEvent we)
		{
		}

		public void windowDeiconified(WindowEvent we)
		{
		}

		public void windowClosed(WindowEvent we)
		{
		}

		public void windowDeactivated(WindowEvent we)
		{
		}

		public void windowActivated(WindowEvent we)
		{
		}

		public void windowOpened(WindowEvent we)
		{
		}
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == wordrap )
			{
				System.out.println(wordrap.getState());
				/*boolean agd = wordrap.getState();
				if (agd.equals(true))
				{
					System.out.println(wordrap.getState());
				}*/
			}
			area.setBackground(Color.WHITE);
			area.setForeground(Color.black);
			area.setEditable(true);
			
			FileDialog fdopen = new FileDialog(this,"Open",FileDialog.LOAD);
			FileDialog fdsave = new FileDialog(this,"Save",FileDialog.SAVE);
			
			if (e.getSource() == feat)
			{
				String msg = "WELCOME TO ChandraPAD DEVELOPED BY CHANDRASHEKHAR AZAD" + "\n" + "\n" + "ChandraPAD is identical to the 'NOTEPAD' provided by 'WINDOWS'" + "\n" + "\n" + "Email all queries to : cazad3011@gmail.com";
				
				area.setText(msg);
				area.setForeground(Color.black);
				area.setEditable(false);
			}
			
			if (e.getSource() == td)
			{
				String text2=area.getSelectedText();
			
				if (text2.equals(""))
				{
					  gposdel = area.getCaretPosition();
					area.insertText(stime,gposdel);
					f=3;
				}
				else
				{
					gposdel = area.getCaretPosition();
					int hg = area.getSelectionStart();
					int gh = area.getSelectionEnd();
					area.replaceRange(stime,hg,gh);
					f=3;
				}
			}
			
			
			if(e.getSource() == undo)
			{
				switch(f)
				{
					case 4:
					{
						//gposdel = area.getCaretPosition();
						//int del = ta.getText().indexOf(deltree);
						area.insert(stime,gposdel);
						f = 3;
						break;
					}
					
					case 3:
					{
						//gposdel = area.getCaretPosition();
						//int del = ta.getText().indexOf(deltree);
						area.replaceRange("",gposdel,gposdel+stime.length());
						
						f = 4;
						
						break;
					}
					
				case 1:
					{
						//gposdel = area.getCaretPosition();
						//int del = ta.getText().indexOf(deltree);
					
						
						area.insert(text,gposdel);
						f = 2;
						
						
						break;
					}
					
					case 2:
					{
						//gposdel = area.getCaretPosition();
						//int del = ta.getText().indexOf(deltree);
						area.replaceRange("",gposdel,gposdel+text.length());
						
						f = 1;
						
						break;
					}
					
					case -1:
					{
						//gposdel = area.getCaretPosition();
						//int del = ta.getText().indexOf(deltree);
						//area.replaceRange("",gposdel,gposdel+deltree.length());
						//area.replaceRange("",gposcut,gposcut+text.length());
						
						f = 1;
						text = area.getText();
						area.setText("");
						
						break;
					}
				}
			}
			
			
			if(e.getSource() == news)
			{
				area.setText("");
			}
			
			if(e.getSource() == print)
			{
				try
				{
				printme.print();
				}
				catch(Exception ew)
				{}
			}
			
			if(e.getSource() == pgset)
			{
				printme.printDialog();
			}
			
			if(e.getSource() == selectall)
			{
				area.selectAll();
			}
			
			if(e.getSource() == about)
			{
				JOptionPane.showMessageDialog(null,"Chandrapad Vs.1.1 Developed By CHANDRASHEKHAR AZAD" + "\n" + "Email me at - cazad3011@hotmail.com");
			}
			
			if (e.getSource() == exit)
			{
				System.out.println("Thank You For Using Chandrapad");
				System.exit(0);
			}
			
			if (e.getSource() == save)
			{
				if (flag.equals("y"))
				{ 
				File delme = new File(directory,filee);
				
				String a = directory;
				String b = filee;
				try
				{
					delme.delete();
				}
				catch(SecurityException eeme)
				{
					System.out.println(eeme);
				}
				File filesaveit = new File(a,b);
				
				try
				{
					RandomAccessFile ram = new RandomAccessFile(a+b,"rw");
					//ram.seek(ram.length());
					String msh = area.getText();
					ram.writeBytes(msh);
					ram.close();
				}
				catch(Exception rt)
				{
					System.out.println(rt);
				}
				}
				
				if (flag.equals("n"))
				{
					fdsave.setVisible(true);
				String dir = fdsave.getDirectory();
				String fil = fdsave.getFile();
				File filesave = new File(dir,fil);
				
				try
				{
					RandomAccessFile ram = new RandomAccessFile(fdsave.getDirectory()+fdsave.getFile()+".txt","rw");
					ram.seek(ram.length());
					String msh = area.getText();
					ram.writeBytes(msh);
					ram.close();
				}
				catch(Exception rt)
				{
					System.out.println(rt);
				}
				}
			}
			
			if (e.getSource()==open)
			{
				flag = "y";
				fdopen.setVisible(true);
				filee = fdopen.getFile();
				directory = fdopen.getDirectory();
				File openfile = new File(directory,filee);
				
				try
				{
					RandomAccessFile ram2 = new RandomAccessFile(openfile,"rw");
					
					long size = ram2.length();
					String msg = "";
					
					for(int counter=0;counter<size;counter++)
					{
						char words = (char)ram2.read();
						msg = msg + words;
					}
					area.setText(msg);
					
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
			
			if (e.getSource() == saveas)
			{
				
				fdsave.setVisible(true);
				String dir = fdsave.getDirectory();
				String fil = fdsave.getFile();
				File filesave = new File(dir,fil);
				
				try
				{
					RandomAccessFile ram = new RandomAccessFile(fdsave.getDirectory()+fdsave.getFile()+".txt","rw");
					ram.seek(ram.length());
					String msh = area.getText();
					ram.writeBytes(msh);
					ram.close();
				}
				catch(Exception rt)
				{
					System.out.println(rt);
				}
			}
			
			if(e.getSource() == copy)
			{
				text = area.getSelectedText();
			}
			
			if(e.getSource() == paste)
			{
				if (text.equals(""))
				{
					gposdel = area.getCaretPosition();
					area.insertText(text,gposdel);
					f=2;
				}
				else
				{
					gposdel = area.getCaretPosition();
					int hg = area.getSelectionStart();
					int gh = area.getSelectionEnd();
					area.replaceRange(text,hg,gh);
					f=2;
				}
			}
			
			if(e.getSource() == delete)
			{
				f = 1;
				text = area.getSelectedText();
				gposdel = area.getCaretPosition();
				//int del = ta.getText().indexOf(deltree);
				area.replaceRange("",gposdel,gposdel+text.length());
				
				
			}
			
			if(e.getSource() == cut)
			{
				f = 1;
				text = area.getSelectedText();
				gposdel = area.getCaretPosition();
				//int del = ta.getText().indexOf(text);
				area.replaceRange("",gposdel,gposdel+text.length());
			}
			
			if(e.getSource() == find)
			{
				searchall = JOptionPane.showInputDialog("Enter The Word You Want To Search");
			}
		}
		
		public static void main(String args[])
		{
			Notepad note = new Notepad();
		}


	}


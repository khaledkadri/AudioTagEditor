/*                                     
 * Author : Khaled KADRI   
 * Copyright (C) 2024 Khaled KADRI
 * LinkedIn : https://www.linkedin.com/in/khaled-kadri/
 * GitHub : https://github.com/khaledkadri
 * License: GNU General Public License v3.0 (GPLv3)
 * This code can be used, modified, and redistributed under GPLv3. 
 * For more details, visit https://www.gnu.org/licenses/gpl-3.0.html
 */

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LookAndFeel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JScrollPane;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

public class EditMoreThanTags extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_titre;
	private JTextField tf_artiste_orig;
	private JTextField tf_artiste;
	private JTextField tf_album;
	private JTextField tf_date;
	private JTextField tf_desc;
	private JTextField tf_compositeur;
	private JTextField tf_piste;
	JTable list = new JTable();
	Model model;
	ArrayList<ArrayList> liste_lect = new ArrayList<ArrayList>();
	ArrayList<String> liste_path_lect = new ArrayList<String>();
	static Media player = new Media();
	int row;
	String image_lab;
	final JTextField searchField;
	String searched = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMoreThanTags frame = new EditMoreThanTags();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static void initplayer(Canvas c){
		player.loadLibrary();
        player.mediaPlayerFactory = new MediaPlayerFactory();
        player.mediaPlayer = player.mediaPlayerFactory.newEmbeddedMediaPlayer();
		player.mediaPlayer.setVideoSurface(player.mediaPlayerFactory.newVideoSurface(c));
		player.mediaPlayer.setEnableMouseInputHandling(false);
		player.mediaPlayer.setEnableKeyInputHandling(false);
	}
	/**
	 * Create the frame.
	 */
	
	public EditMoreThanTags() {
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("/image/icon_frame3.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Audio Tag Editor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1172, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JMenuBar jmb = new JMenuBar();
		JMenu jmenu = new JMenu("About");
		JMenuItem aide = new JMenuItem("Help");
		JMenuItem apropos = new JMenuItem("About");
		//jmenu.add(aide);
		jmenu.add(apropos);
		jmb.add(jmenu);
		contentPane.add(jmb,BorderLayout.NORTH);
		apropos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame frame= new JFrame("About");
                frame.setVisible(true);
                frame.setSize(new Dimension(1000,300));
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame.setBackground(new Color(183,206,179));
                Font font = new Font("Courier new", Font.PLAIN, 16);
                
                JPanel p1 = new JPanel();
                p1.setBackground(new Color(255,255,255));
                
                final JTextPane textPane = new JTextPane();
                textPane.setContentType("text/html");
                String link = "<a href=\"https://www.linkedin.com/in/khaled-kadri/\" style=\"color: #0077B5; text-decoration: none; font-weight: bold;\">LinkedIn</a><br>"
                        + "<a href=\"https://github.com/khaledkadri\" style=\"color: #333; text-decoration: none; font-weight: bold;\">GitHub</a>";

                String licence = "<p style=\"margin-top: 15px;\">This application incorporates <a href=\"https://github.com/mpatric/mp3agic\" style=\"color: #0077B5; text-decoration: none;\">mp3agic</a> (MIT License) and <a href=\"https://github.com/caprica/vlcj\" style=\"color: #0077B5; text-decoration: none;\">vlcj</a> (GPLv3).</p>";
                licence += "<p style=\"margin-top: 15px;\">This application is free for non-commercial use, and must comply with the <a href=\"https://www.gnu.org/licenses/gpl-3.0.en.html\" style=\"color: #0077B5; text-decoration: none;\">GPLv3</a> license.</p>";

                textPane.setText("<html><body style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333; line-height: 1.6;\">" +
                           "<br><b>Author: Khaled KADRI</b><br>" +
                           "<p>Connect with me on:</p>" +
                           link +
                           licence +
                           "</body></html>");

                textPane.setEditable(false);
                textPane.setBackground(new Color(182,206,179));
                textPane.setFont(font);
                textPane.setOpaque(false);
                textPane.setCursor(new Cursor(Cursor.HAND_CURSOR));

                textPane.addHyperlinkListener(new HyperlinkListener() {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            try {
                                Desktop.getDesktop().browse(URI.create(e.getURL().toString()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                                
                p1.add(textPane,BorderLayout.CENTER);
                frame.getContentPane().add(p1);
                frame.setResizable(false);
                
			}
		});
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(388, 13, 8, 552);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(12, 13, 364, 494);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(83, 29, 33, 16);
		panel_1.add(lblTitle);
		
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblArtist.setBounds(77, 60, 46, 16);
		panel_1.add(lblArtist);
		
		JLabel lblAlbum = new JLabel("Album");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAlbum.setBounds(69, 88, 47, 16);
		panel_1.add(lblAlbum);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenre.setBounds(74, 118, 42, 16);
		panel_1.add(lblGenre);
		
		JLabel lblAnne = new JLabel("Year");
		lblAnne.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnne.setBounds(85, 146, 46, 16);
		panel_1.add(lblAnne);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(37, 175, 79, 16);
		panel_1.add(lblDescription);
		
		JLabel lblCompositeur = new JLabel("Composer");
		lblCompositeur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCompositeur.setBounds(45, 204, 90, 16);
		panel_1.add(lblCompositeur);
		
		JLabel lblArtistOriginal = new JLabel("Original artist");
		lblArtistOriginal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblArtistOriginal.setBounds(23, 233, 104, 17);
		panel_1.add(lblArtistOriginal);
		
		tf_titre = new JTextField();
		tf_titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_titre.setBounds(123, 23, 208, 29);
		panel_1.add(tf_titre);
		tf_titre.setColumns(10);
		
		tf_artiste_orig = new JTextField();
		tf_artiste_orig.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_artiste_orig.setColumns(10);
		tf_artiste_orig.setBounds(123, 226, 208, 29);
		panel_1.add(tf_artiste_orig);
		
		tf_artiste = new JTextField();
		tf_artiste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_artiste.setColumns(10);
		tf_artiste.setBounds(123, 52, 208, 29);
		panel_1.add(tf_artiste);
		
		tf_album = new JTextField();
		tf_album.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_album.setColumns(10);
		tf_album.setBounds(123, 81, 208, 29);
		panel_1.add(tf_album);
		
		tf_date = new JTextField();
		tf_date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_date.setColumns(10);
		tf_date.setBounds(123, 139, 63, 29);
		panel_1.add(tf_date);
		
		tf_desc = new JTextField();
		tf_desc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_desc.setColumns(10);
		tf_desc.setBounds(123, 168, 208, 29);
		panel_1.add(tf_desc);
		
		tf_compositeur = new JTextField();
		tf_compositeur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_compositeur.setColumns(10);
		tf_compositeur.setBounds(123, 197, 208, 29);
		panel_1.add(tf_compositeur);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pop", "Rock", "Hip-Hop", "R&B", "Dance", "Electronic", "Jazz", "Country", "Classical", "Indie", "Reggae", "Funk", "Disco", "Alternative", "Blues", "Soul", "Metal", "Grunge", "Techno", "Oldies", "New Age", "Vocal", "Acoustic", "Instrumental", "Folk", "Salsa", "Tango", "Opera", "Symphony", "Soundtrack", "Club", "Trance", "Dream", "Beat", "Anime", "Folklore", "Slow Rock", "Class Rock", "Alt Rock", "Speech", "Samba"}));

		comboBox.setSelectedIndex(0);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(123, 110, 208, 29);
		panel_1.add(comboBox);
		
		final JCheckBox ch_titre = new JCheckBox("");
		ch_titre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_titre.isSelected())
					tf_titre.setEnabled(false);
				else
					tf_titre.setEnabled(true);
			}
		});
		ch_titre.setSelected(true);
		ch_titre.setBounds(337, 26, 25, 25);
		panel_1.add(ch_titre);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(143, 302, 170, 170);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		final JLabel label_img = new JLabel("");
		panel_2.add(label_img, BorderLayout.CENTER);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 278, 354, 2);
		panel_1.add(separator_1);
		
		final JButton bt_add_img = new JButton("");
		bt_add_img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = null ;
				LookAndFeel previousLF = UIManager.getLookAndFeel();
				try {
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				        chooser = new JFileChooser();
				        UIManager.setLookAndFeel(previousLF);
						
					} catch (ClassNotFoundException
							| InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					chooser.setMultiSelectionEnabled(true) ;
					File f=null;
		            int resultOfChoice;
		            FileInputStream in;
		            
		            FileFilter imageFilter = new FileNameExtensionFilter(
		            	    "Image files", ImageIO.getReaderFileSuffixes());
		            chooser.setFileFilter(imageFilter);
		            
		             chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		             String musicFolder = System.getProperty("user.home") + "\\Music";
		             chooser.setCurrentDirectory(new File(musicFolder));
		             int retour = chooser.showOpenDialog(null);
		             if(retour == JFileChooser.APPROVE_OPTION){
		            	
		            	File file = chooser.getSelectedFile();
		            	image_lab = file.getAbsolutePath();
		            	image_lab = image_lab.replace('\\', '/');
			    		image_lab = "file:///"+image_lab;
		            	
		            	Image img;
						try {
							img = ImageIO.read(file);
							img = (Image) img.getScaledInstance(170, 155, Image.SCALE_SMOOTH);
				    		label_img.setIcon(new ImageIcon(img));
				    		
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    		
		             }
			}
		});
		bt_add_img.setIcon(new ImageIcon(EditMoreThanTags.class.getResource("/image/20074.png")));
		bt_add_img.setBounds(78, 368, 38, 37);
		panel_1.add(bt_add_img);
		
		final JCheckBox ch_image = new JCheckBox("");
		ch_image.setFont(new Font("Verdana", Font.PLAIN, 14));
		ch_image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_image.isSelected()){
					bt_add_img.setEnabled(false);
				}
				else{
					bt_add_img.setEnabled(true);
				}
			}
		});
		ch_image.setSelected(true);
		ch_image.setBounds(337, 372, 25, 25);
		panel_1.add(ch_image);
		
		JLabel lblTrack = new JLabel("Piste");
		lblTrack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrack.setBounds(229, 146, 34, 16);
		panel_1.add(lblTrack);
		
		tf_piste = new JTextField();
		tf_piste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_piste.setColumns(10);
		tf_piste.setBounds(268, 139, 63, 29);
		panel_1.add(tf_piste);
		
		final JCheckBox ch_date = new JCheckBox("");
		ch_date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_date.isSelected())
					tf_date.setEnabled(false);
				else
					tf_date.setEnabled(true);
			}
		});
		ch_date.setSelected(true);
		ch_date.setBounds(194, 142, 25, 25);
		panel_1.add(ch_date);
		
		final JCheckBox ch_artiste = new JCheckBox("");
		ch_artiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_artiste.isSelected())
					tf_artiste.setEnabled(false);
				else
					tf_artiste.setEnabled(true);
			}
		});
		ch_artiste.setSelected(true);
		ch_artiste.setBounds(337, 57, 25, 25);
		panel_1.add(ch_artiste);
		
		final JCheckBox ch_album = new JCheckBox("");
		ch_album.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_album.isSelected())
					tf_album.setEnabled(false);
				else
					tf_album.setEnabled(true);
			}
		});
		ch_album.setSelected(true);
		ch_album.setBounds(337, 85, 25, 25);
		panel_1.add(ch_album);
		
		final JCheckBox ch_genre = new JCheckBox("");
		ch_genre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_genre.isSelected())
					comboBox.setEnabled(false);
				else
					comboBox.setEnabled(true);
			}
		});
		ch_genre.setSelected(true);
		ch_genre.setBounds(337, 115, 25, 25);
		panel_1.add(ch_genre);
		
		final JCheckBox ch_track = new JCheckBox("");
		ch_track.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_track.isSelected())
					tf_piste.setEnabled(false);
				else
					tf_piste.setEnabled(true);
			}
		});
		ch_track.setSelected(true);
		ch_track.setBounds(337, 143, 25, 25);
		panel_1.add(ch_track);
		
		final JCheckBox ch_desc = new JCheckBox("");
		ch_desc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_desc.isSelected())
					tf_desc.setEnabled(false);
				else
					tf_desc.setEnabled(true);
			}
		});
		ch_desc.setSelected(true);
		ch_desc.setBounds(337, 171, 25, 25);
		panel_1.add(ch_desc);
		
		final JCheckBox ch_compositeur = new JCheckBox("");
		ch_compositeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_compositeur.isSelected())
					tf_compositeur.setEnabled(false);
				else
					tf_compositeur.setEnabled(true);
			}
		});
		ch_compositeur.setSelected(true);
		ch_compositeur.setBounds(337, 201, 25, 25);
		panel_1.add(ch_compositeur);
		
		final JCheckBox ch_artiste_orig = new JCheckBox("");
		ch_artiste_orig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ch_artiste_orig.isSelected())
					tf_artiste_orig.setEnabled(false);
				else
					tf_artiste_orig.setEnabled(true);
			}
		});
		ch_artiste_orig.setSelected(true);
		ch_artiste_orig.setBounds(337, 230, 25, 25);
		panel_1.add(ch_artiste_orig);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(408, 13, 600, 30);
		panel.add(toolBar);

		
		final String[] titres= new String[7];
		titres[0]="#";
		titres[1]="Title ";
		titres[2]="Duration";titres[3]="Album";titres[4]="Artist";
		titres[5]="Genre";titres[6]="Date";
		
		final JButton btnAjouter = new JButton("Choose folder");
        ImageIcon originalIcon = new ImageIcon(EditMoreThanTags.class.getResource("/image/folder.png"));
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
		btnAjouter.setIcon(new ImageIcon(resizedImg));
        btnAjouter.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnAjouter.setFont(new Font("Arial", Font.BOLD, 14));
        
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser chooser = null ;
				LookAndFeel previousLF = UIManager.getLookAndFeel();
				try {
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				        chooser = new JFileChooser();
				        UIManager.setLookAndFeel(previousLF);
						
					} catch (ClassNotFoundException
							| InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					chooser.setMultiSelectionEnabled(true) ;
					File f=null;
		            int resultOfChoice;
		            FileInputStream in;
		            /*FileFilter imageFilter = new FileNameExtensionFilter(
		            	    "Media files", ImageIO.getReaderFileSuffixes());
		            chooser.setFileFilter(imageFilter);*/
		            
		            FileFilter filter = new FileNameExtensionFilter("mp3","mpe","mp4","flv","avi","mkv","mpeg","mpg","divx","mov","dat","ogg","3gp","srt",
							"wmv","bup","dts","gxf","m2v","m4v","ogm","a52","dv","vob","bin","ifo","part"
							,"3g2","rmvb","asf");
		            chooser.addChoosableFileFilter(filter);
		            chooser.setFileFilter(filter);
		            
		            final ArrayList<String> extensions = new java.util.ArrayList<String>();
		            String[] exts={"mp3","mpe","mp4","flv","avi","mkv","mpeg","mpg","divx","mov","dat","ogg","3gp","srt",
							"wmv","bup","dts","gxf","m2v","m4v","ogm","a52","dv","vob","bin","ifo","part"
							,"3g2","rmvb","asf"};
		            for (String ext : exts) {
		            	extensions.add(ext);
		            }
		            chooser.addChoosableFileFilter(new FileFilter() {
						
						@Override
						public String getDescription() {
							return "";
						}
						
						@Override
						public boolean accept(File f) {
							if (extensions == null) return false;
							
					        // Allows files with extensions specified to be seen.
					        for (String ext : extensions) {
					            if (f.getName().toLowerCase().endsWith("." + ext))
					                return true;
					        }
					        
					        // Otherwise file is not shown.
					        return false;
						}
					});
		            
		            String musicFolder = System.getProperty("user.home") + "\\Music";
		             chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		             //chooser.setSelectedFile(new File(System.getProperty("user.dir")));
		             chooser.setCurrentDirectory(new File(musicFolder));
		             
		             int retour = chooser.showOpenDialog(null);
		             if(retour == JFileChooser.APPROVE_OPTION){
		                File[] fs=chooser.getSelectedFiles();
		                for( int i = 0; i<fs.length; ++i){ 
		                   listFile(fs[i]);
		                }
		                
		                Object[][] donnees = new Object[liste_lect.size()][7];
						
						for(int i = 0 ; i < liste_lect.size() ; i++){
							donnees[i][0]=i+1;
							try {
							    donnees[i][1] = new String(liste_lect.get(i).get(0).toString().getBytes(), "UTF-8");
							    donnees[i][2] = new String(liste_lect.get(i).get(1).toString().getBytes(), "UTF-8");
							    donnees[i][3] = new String(liste_lect.get(i).get(2).toString().getBytes(), "UTF-8");
							    donnees[i][4] = new String(liste_lect.get(i).get(3).toString().getBytes(), "UTF-8");
							    donnees[i][5] = new String(liste_lect.get(i).get(4).toString().getBytes(), "UTF-8");
							    donnees[i][6] = new String(liste_lect.get(i).get(5).toString().getBytes(), "UTF-8");
							} catch (UnsupportedEncodingException e) {
							    e.printStackTrace();
							}

						}
			    		model = new Model(donnees, titres);
			    		createContent(donnees, titres, list);
			    	    centrerTable(list);
		             }
			}
		});
		
		
		// search field
		searchField = new JTextField(20) {
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);

		        if (getText().isEmpty() && !isFocusOwner()) {
		            Graphics2D g2 = (Graphics2D) g;
		            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		            g2.setColor(new Color(150, 150, 150)); // Placeholder color
		            g2.drawString("Search...", getInsets().left + 5, g.getFontMetrics().getMaxAscent() + 5);
		        }
		    }
		};
		searchField.setFont(new Font("Verdana", Font.PLAIN, 14));
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setBackground(new Color(245, 245, 245));
		searchField.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)),
		        BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		searchField.setPreferredSize(new Dimension(250, 30));
		searchField.setBorder(BorderFactory.createCompoundBorder(
		        BorderFactory.createLineBorder(new Color(180, 180, 180), 1), 
		        BorderFactory.createEmptyBorder(5, 10, 5, 10) 
		));
		searchField.setForeground(new Color(150, 150, 150)); 

        toolBar.add(searchField);

        createContent(new Object[0][7], titres,list);
        
		list.addMouseListener(new MouseAdapter() {
			Point p;
			public void mousePressed(MouseEvent e) {
				JTable table =(JTable) e.getSource();
				p = e.getPoint();
				row = table.rowAtPoint(p);
				row = (int) table.getValueAt(row, 0)-1;
				
				String utf8Text;
				try {
					
					    // Vérifiez et définissez le titre
					    if (!isUtf8Encoded(liste_lect.get(row).get(0).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(0).toString().getBytes(), "UTF-8");
					        tf_titre.setText(utf8Text);
					    } else {
					        tf_titre.setText(liste_lect.get(row).get(0).toString());
					    }

					    // Vérifiez et définissez l'artiste
					    if (!isUtf8Encoded(liste_lect.get(row).get(2).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(2).toString().getBytes(), "UTF-8");
					        tf_artiste.setText(utf8Text);
					    } else {
					        tf_artiste.setText(liste_lect.get(row).get(2).toString());
					    }

					    // Vérifiez et définissez l'album
					    if (!isUtf8Encoded(liste_lect.get(row).get(3).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(3).toString().getBytes(), "UTF-8");
					        tf_album.setText(utf8Text);
					    } else {
					        tf_album.setText(liste_lect.get(row).get(3).toString());
					    }

					    // Vérifiez et définissez la date
					    if (!isUtf8Encoded(liste_lect.get(row).get(5).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(5).toString().getBytes(), "UTF-8");
					        tf_date.setText(utf8Text);
					    } else {
					        tf_date.setText(liste_lect.get(row).get(5).toString());
					    }

					    // Vérifiez et définissez la piste
					    if (!isUtf8Encoded(liste_lect.get(row).get(6).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(6).toString().getBytes(), "UTF-8");
					        tf_piste.setText(utf8Text);
					    } else {
					        tf_piste.setText(liste_lect.get(row).get(6).toString());
					    }

					    // Vérifiez et définissez la description
					    if (!isUtf8Encoded(liste_lect.get(row).get(7).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(7).toString().getBytes(), "UTF-8");
					        tf_desc.setText(utf8Text);
					    } else {
					        tf_desc.setText(liste_lect.get(row).get(7).toString());
					    }

					    // Vérifiez et définissez le compositeur
					    if (!isUtf8Encoded(liste_lect.get(row).get(8).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(8).toString().getBytes(), "UTF-8");
					        tf_compositeur.setText(utf8Text);
					    } else {
					        tf_compositeur.setText(liste_lect.get(row).get(8).toString());
					    }

					    // Vérifiez et définissez l'artiste original
					    if (!isUtf8Encoded(liste_lect.get(row).get(9).toString())) {
					        utf8Text = new String(liste_lect.get(row).get(9).toString().getBytes(), "UTF-8");
					        tf_artiste_orig.setText(utf8Text);
					    } else {
					        tf_artiste_orig.setText(liste_lect.get(row).get(9).toString());
					    }
				    
				} catch (UnsupportedEncodingException e1) {
				    // Handle the exception
				    e1.printStackTrace();
				}

				if(liste_lect.get(row).get(10)!=null){
					Image img = null;
			    	try {
			    		if(liste_lect.get(row).get(10)!=null){
			    			if(!liste_lect.get(row).get(10).toString().equals("empty")){
			    				img = ImageIO.read(new URL(liste_lect.get(row).get(10).toString()));
				    			img = (Image) img.getScaledInstance(170, 170, Image.SCALE_SMOOTH);
				    			label_img.setIcon(new ImageIcon(img));
			    			}
			    			else {
			    				label_img.setIcon(null);
			    			}
			    		}
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	
			    }
			}
		});
		
		toolBar.add(btnAjouter);
		
		/*JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);
		
		JButton btnSelectionnerTout = new JButton("Select all");
		btnSelectionnerTout.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSelectionnerTout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.selectAll();
			}
		});*/
		//toolBar.add(btnSelectionnerTout);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_3);
		
		JButton btnSupprimer = new JButton("Delete");
        originalIcon = new ImageIcon(EditMoreThanTags.class.getResource("/image/clear.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        btnSupprimer.setIcon(new ImageIcon(resizedImg));
        btnSupprimer.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSupprimer.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((Model)list.getModel()).removeRow(row);
				liste_lect.remove(row);
				liste_path_lect.remove(row);
			}
		});
		toolBar.add(btnSupprimer);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_4);
		
		JButton btnSupprimerTout = new JButton("Delete all");
		originalIcon = new ImageIcon(EditMoreThanTags.class.getResource("/image/delete.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        btnSupprimerTout.setIcon(new ImageIcon(resizedImg));
        btnSupprimerTout.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSupprimerTout.setFont(new Font("Arial", Font.BOLD, 14));
		btnSupprimerTout.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSupprimerTout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while(list.getRowCount()!=0)
					((Model)list.getModel()).removeRow(0);
				liste_lect.clear();
				liste_path_lect.clear();
			}
		});
		toolBar.add(btnSupprimerTout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(402, 43, 753, 522);
		scrollPane.setViewportView(list);
		panel.add(scrollPane);
		
		final JButton btnValider = new JButton("Save");
		originalIcon = new ImageIcon(EditMoreThanTags.class.getResource("/image/save.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
        btnValider.setIcon(new ImageIcon(resizedImg));
        btnValider.setBackground(new Color(76, 175, 80));
        btnValider.setForeground(Color.WHITE);
        btnValider.setFont(new Font("Arial", Font.BOLD, 14));
        btnValider.setBorderPainted(false);
        btnValider.setFocusPainted(false);
        btnValider.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnValider.setBackground(new Color(56, 142, 60)); 
                btnValider.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnValider.setBackground(new Color(76, 175, 80));
                btnValider.setForeground(Color.WHITE);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnValider.setBackground(new Color(46, 125, 50));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnValider.setBackground(new Color(56, 142, 60));
            }
        });
        
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titre="",artiste="",album="",genre="",date="",piste="",desc="",
						compositeur="",artiste_orig="",image="",path="";
				//int[] selected_rows = list.getSelectedRows();
				int selectedRow = list.getSelectedRow();
				int selected_rows = (int) list.getValueAt(selectedRow, 0)-1;
				
					try {
					    titre = new String(tf_titre.getText().getBytes("UTF-8"), "UTF-8");
					    artiste = new String(tf_artiste.getText().getBytes("UTF-8"), "UTF-8");
					    album = new String(tf_album.getText().getBytes("UTF-8"), "UTF-8");

					    if (comboBox.isEnabled()) {
					        genre = new String(comboBox.getSelectedItem().toString().getBytes("UTF-8"), "UTF-8");
					    } else {
					        genre = " ";
					    }

					    date = new String(tf_date.getText().getBytes("UTF-8"), "UTF-8");
					    piste = new String(tf_piste.getText().getBytes("UTF-8"), "UTF-8");
					    desc = new String(tf_desc.getText().getBytes("UTF-8"), "UTF-8");
					    compositeur = new String(tf_compositeur.getText().getBytes("UTF-8"), "UTF-8");
					    artiste_orig = new String(tf_artiste_orig.getText().getBytes("UTF-8"), "UTF-8");
					    
					} catch (UnsupportedEncodingException e1) {
					    e1.printStackTrace();
					}

					
					if(ch_image.isSelected())
						image = image_lab;
					else
						image = liste_lect.get(selected_rows).get(10).toString();
					
					Image img = null;
					if(image!=null)
					if(!image.equals("empty")){
				    	try {
				    		if(liste_lect.get(selected_rows).get(10)!=null){
				    			img = ImageIO.read(new URL(image));
				    			//img = (Image) img.getScaledInstance(170, 155, Image.SCALE_SMOOTH);
				    		}
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	
				    }
					byte[] image_byte = null;
					if(img!=null){
						BufferedImage bufferedImage = toBufferedImage(img);
						byte[] imageInByte = null;
		   				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   				try {
							ImageIO.write( bufferedImage, "jpg", baos );
							baos.flush();
			   				imageInByte = baos.toByteArray();
			   				baos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		   				image_byte = imageInByte;
					}
					path = liste_path_lect.get(selected_rows);
					player.settags(titre, artiste, album, genre, date, piste,desc,
							compositeur,artiste_orig,image_byte, path);
					
					liste_lect.get(selected_rows).set(0, titre);
					liste_lect.get(selected_rows).set(2, artiste);
					liste_lect.get(selected_rows).set(3, album);
					liste_lect.get(selected_rows).set(4, genre);
					liste_lect.get(selected_rows).set(5, date);
					liste_lect.get(selected_rows).set(6, piste);
					liste_lect.get(selected_rows).set(7, desc);
					liste_lect.get(selected_rows).set(8, compositeur);
					liste_lect.get(selected_rows).set(9, artiste_orig);

					liste_lect.get(selected_rows).set(10, image);
					
					JOptionPane.showMessageDialog(EditMoreThanTags.this, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					
					// update table
					selectedRow = list.getSelectedRow();
					selectedRow = (int) list.getValueAt(selectedRow, 0)-1;
					
					Object[][] donnees = new Object[liste_lect.size()][7];
					for(int i1 = 0 ; i1 < liste_lect.size() ; i1++){
						donnees[i1][0]=i1+1;
						try {
							if (!isUtf8Encoded(liste_lect.get(i1).get(0).toString())) {
						        donnees[i1][1] = new String(liste_lect.get(i1).get(0).toString().getBytes(), "UTF-8");
						    } else {
						        donnees[i1][1] = new String(liste_lect.get(i1).get(0).toString());
						    }

						    if (!isUtf8Encoded(liste_lect.get(i1).get(1).toString())) {
						        donnees[i1][2] = new String(liste_lect.get(i1).get(1).toString().getBytes(), "UTF-8");
						    } else {
						        donnees[i1][2] = new String(liste_lect.get(i1).get(1).toString());
						    }

						    if (!isUtf8Encoded(liste_lect.get(i1).get(2).toString())) {
						        donnees[i1][3] = new String(liste_lect.get(i1).get(2).toString().getBytes(), "UTF-8");
						    } else {
						        donnees[i1][3] = new String(liste_lect.get(i1).get(2).toString());
						    }

						    if (!isUtf8Encoded(liste_lect.get(i1).get(3).toString())) {
						        donnees[i1][4] = new String(liste_lect.get(i1).get(3).toString().getBytes(), "UTF-8");
						    } else {
						        donnees[i1][4] = new String(liste_lect.get(i1).get(3).toString());
						    }

						    if (!isUtf8Encoded(liste_lect.get(i1).get(4).toString())) {
						        donnees[i1][5] = new String(liste_lect.get(i1).get(4).toString().getBytes(), "UTF-8");
						    } else {
						        donnees[i1][5] = new String(liste_lect.get(i1).get(4).toString());
						    }

						    if (!isUtf8Encoded(liste_lect.get(i1).get(5).toString())) {
						        donnees[i1][6] = new String(liste_lect.get(i1).get(5).toString().getBytes(), "UTF-8");
						    } else {
						        donnees[i1][6] = new String(liste_lect.get(i1).get(5).toString());
						    }
						} catch (UnsupportedEncodingException e1) {
						    e1.printStackTrace();
						}

					}
		    		model = new Model(donnees, titres);
		    		createContent(donnees, titres, list);
		    	    centrerTable(list);
		    	    searchField.setText(searched);
		    	    if(searched.isEmpty())
		    	    list.setRowSelectionInterval(selectedRow,selectedRow);
					
			}
		});
		btnValider.setBounds(138, 520, 114, 35);
		panel.add(btnValider);
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		Canvas canvas = new Canvas();
		initplayer(canvas);
	}
	
	public static boolean isUtf8Encoded(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
        String decodedText = new String(bytes, StandardCharsets.UTF_8);
        return text.length() == decodedText.length();
    }
	
	public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }
	
	/**********************************************************************************************************************************/
	private void createContent(Object[][] data, String title[], JTable tableau) {

	    model = new Model(data, title);
	    tableau.setModel(model);
	    tableau.setRowHeight(25);

	    for (int j = 0; j < title.length; j++) {
	        TableColumn col = tableau.getColumnModel().getColumn(j);
	        if (j == 0)
	            col.setPreferredWidth(50);
	        if (j == 1)
	            col.setPreferredWidth(400);
	    }

	    JTableHeader header = tableau.getTableHeader();
	    header.setFont(new Font("Verdana", Font.BOLD, 14)); 
	    header.setForeground(Color.WHITE); 
	    header.setBackground(new Color(70, 130, 180)); 
	    header.setBorder(BorderFactory.createLineBorder(Color.GRAY)); 

	    tableau.setFont(new Font("Verdana", Font.PLAIN, 14));
	    tableau.setForeground(Color.BLACK); 
	    tableau.setBackground(new Color(250, 250, 250)); 
	    tableau.setGridColor(Color.LIGHT_GRAY);
	    tableau.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    final TableRowSorter<Model> sorter = new TableRowSorter<>(model);
	    tableau.setRowSorter(sorter);

	    searchField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            filter();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            filter();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            filter();
	        }

	        private void filter() {
	            final String query = searchField.getText().toLowerCase();
	            searched = searchField.getText();
	            if (query.trim().length() == 0) {
	                sorter.setRowFilter(null);
	            } else {
	                sorter.setRowFilter(new RowFilter<Model, Integer>() {
	                    @Override
	                    public boolean include(RowFilter.Entry<? extends Model, ? extends Integer> entry) {
	                        for (int i = 0; i < entry.getValueCount(); i++) {
	                            Object value = entry.getValue(i);
	                            if (value != null && value.toString().toLowerCase().contains(query)) {
	                                return true;
	                            }
	                        }
	                        return false;
	                    }
	                });
	            }
	        }

	    });

	    centrerTable(tableau);
	}

	class Model extends AbstractTableModel{
	    private Object[][] data;
	    private String[] title;

	    public Model(Object[][] data, String[] title){
	        this.data = data;
	        this.title = title;
	    }

	    public String getColumnName(int col) {
	        return this.title[col];
	    }

	    public int getColumnCount() {
	        return this.title.length;
	    }

	    public int getRowCount() {
	        return this.data.length;
	    }

	    public Object getValueAt(int row, int col) {
	        return this.data[row][col];
	    }

	    public void setValueAt(Object value, int row, int col) {
	        this.data[row][col] = value;
	    }
	 
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
	    }

	    public void removeRow(int position){
	    	int indice = 0, indice2 = 0, nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
	    	Object temp[][] = new Object[nbRow][nbCol];
	    	for(int i = 0 ; i < nbRow ; i++){
	    		Object[] value=this.data[i];
	    		if(i != position){
	    			temp[indice] = value;
	    			indice++;
	    		}
	    	}
	    	this.data = temp;
	    	temp = null;
	    	this.fireTableDataChanged ();
	    }
	    
	    public void setColorrow(int piste){
	    	Enumeration<TableColumn> en = list.getColumnModel().getColumns();
	    	MyTableCellRenderer dcr = new MyTableCellRenderer(piste); 
	        while (en.hasMoreElements()) {
	            TableColumn tc = en.nextElement();
	            tc.setCellRenderer(new MyTableCellRenderer(piste));
	        }
	    }
	    
	    public class MyTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	    	int pister;
	    	public MyTableCellRenderer(int piste){
	    		pister=piste;
	    	}
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            setForeground(null);
	            setFont(null);
	            setBackground(null);
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            setText(String.valueOf(value));
	            boolean interestingRow = row == pister;
	            boolean secondColumn = column == 1;
	            if (interestingRow) {
	            	setForeground(new Color(43,89,106));
	            	setFont(new Font("Arial",Font.BOLD,16));
	            	setBackground(new Color(240,240,240));
	            }
	    	    for (int i=0 ; i<table.getColumnCount() ; i++)
	    	        table.getColumnModel().getColumn(i).setCellRenderer(this);
	    	   
	            return this;
	        }

	    }
	}


	static void centrerTable(JTable table) {     
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)
	            table.getTableHeader().getDefaultRenderer();
	        renderer.setHorizontalAlignment(JLabel.LEFT);
	    DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
	    custom.setHorizontalAlignment(JLabel.LEFT);
	    for (int i=0 ; i<table.getColumnCount() ; i++)
	        table.getColumnModel().getColumn(i).setCellRenderer(custom);
	}
	
	
	private void listFile(File file){
		int count = 0;
		if(file.isFile()){
			boolean bfolder = false,baas=false;
			String absoluteParent = null;
			File parent = file.getParentFile();
			for(File nom : parent.listFiles()){
				absoluteParent = nom.getAbsolutePath().substring(0,nom.getAbsolutePath().indexOf(nom.getName()));
				if(nom.getName().equals("Folder.jpg")){
					nom.renameTo(new File(absoluteParent+File.separator+"Folder1.jpg"));
					bfolder=true;
				}
				if(nom.getName().equals("AlbumArtSmall.jpg")){
					nom.renameTo(new File(absoluteParent+File.separator+"AlbumArtSmall1.jpg"));
					baas=true;
				}
			}
			
			if(!contenir(file)){
				ArrayList temp = new ArrayList();
				ArrayList temp_nv = new ArrayList();
				
				temp_nv.add(file.getName());
				if (accept(file)){
					player.mediaPlayer.prepareMedia(file.getAbsolutePath());
					player.mediaPlayer.parseMedia();
				int duration = player.getDuration();
				try {
				    temp.add(new String(player.gettitle().getBytes("UTF-8"), "UTF-8"));
				    temp.add(convertir(duration));

				    if (player.getArtist() == null) {
				        temp.add(" ");
				    } else {
				        temp.add(new String(player.getArtist().getBytes("UTF-8"), "UTF-8"));
				    }
				    
				    if (player.getAlbum() == null) {
				        temp.add(" ");
				    } else {
				        temp.add(new String(player.getAlbum().getBytes("UTF-8"), "UTF-8"));
				    }

				    if (player.getGenre() == null) {
				        temp.add(" ");
				    } else {
				        temp.add(new String(player.getGenre().getBytes("UTF-8"), "UTF-8"));
				    }

				    if (player.getDate() == null) {
				        temp.add(" ");
				    } else {
				        temp.add(new String(player.getDate().getBytes("UTF-8"), "UTF-8"));
				    }

				    if (player.getTrack() == null) {
				        temp.add(" ");
				    } else {
				        temp.add(new String(player.getTrack().getBytes("UTF-8"), "UTF-8"));
				    }

				    if (player.getDescription() == null) {
				        temp.add(" ");
				    } else {
				        temp.add(new String(player.getDescription().getBytes("UTF-8"), "UTF-8"));
				    }

				    temp.add(new String(player.getCompositeur(file.getAbsolutePath()).getBytes("UTF-8"), "UTF-8"));
				    temp.add(new String(player.getOriginalArtist(file.getAbsolutePath()).getBytes("UTF-8"), "UTF-8"));

				} catch (UnsupportedEncodingException e) {
				    e.printStackTrace();
				}

			    temp.add(player.getPicture());
			    
				liste_lect.add(temp);
				liste_path_lect.add(file.getAbsolutePath());
			}

			}
			if(bfolder){
				File folder = new File(absoluteParent+File.separator+"Folder1.jpg");
				folder.renameTo(new File(absoluteParent+File.separator+"Folder.jpg"));
			}
			if(baas){
				File folder = new File(absoluteParent+File.separator+"AlbumArtSmall1.jpg");
				folder.renameTo(new File(absoluteParent+File.separator+"AlbumArtSmall.jpg"));
			}
		}
		else{
			boolean bfolder = false,baas=false;
			for(File nom : file.listFiles()){
				if(nom.getName().equals("Folder.jpg")){
					nom.renameTo(new File(file.getAbsolutePath()+File.separator+"Folder1.jpg"));
					bfolder=true;
				}
				if(nom.getName().equals("AlbumArtSmall.jpg")){
					nom.renameTo(new File(file.getAbsolutePath()+File.separator+"AlbumArtSmall1.jpg"));
					baas=true;
				}
			}
			for(File nom : file.listFiles()){
					if(nom.isDirectory()){
						listFile(nom);
					}else{
						String nomf = nom.getName();
						String extension = nomf.substring(nomf.lastIndexOf(".") + 1, nomf.length());
						
						 {
	    					if(nom.isFile()){
	    						if(!contenir(nom)){
	    					ArrayList temp = new ArrayList();
	    					ArrayList temp_nv = new ArrayList();
	    					
	    					temp_nv.add(nom.getName());
	    					if (accept(nom)){
	    						player.mediaPlayer.prepareMedia(nom.getAbsolutePath());
	    						player.mediaPlayer.parseMedia();
	    					int duration = player.getDuration();
	    					temp.add(player.gettitle());
	    					temp.add(convertir(duration));
	    					
	    					if(player.getArtist()==null)temp.add(" ");else temp.add(player.getArtist());
	    					if(player.getAlbum()==null)temp.add(" ");else temp.add(player.getAlbum());
	    					if(player.getGenre()==null)temp.add(" ");else temp.add(player.getGenre());
	    					if(player.getDate()==null)temp.add(" ");else temp.add(player.getDate());
	    					if(player.getTrack()==null)temp.add(" ");else temp.add(player.getTrack());
	    					if(player.getDescription()==null)temp.add(" ");else temp.add(player.getDescription());
	    				    temp.add(player.getCompositeur(nom.getAbsolutePath()));
	    				    temp.add(player.getOriginalArtist(nom.getAbsolutePath()));
	    				    temp.add(player.getPicture());
	    				    
	    					liste_lect.add(temp);
	    					liste_path_lect.add(nom.getAbsolutePath());
	    					}
	    				}
	    				}
					}
				}
			}
			if(bfolder){
				File folder = new File(file.getPath()+File.separator+"Folder1.jpg");
				folder.renameTo(new File(file.getPath()+File.separator+"Folder.jpg"));
			}
			if(baas){
				File folder = new File(file.getPath()+File.separator+"AlbumArtSmall1.jpg");
				folder.renameTo(new File(file.getPath()+File.separator+"AlbumArtSmall.jpg"));
			}
		}
	}
	
	boolean isCaseC(String ex){
		String upperCase,lowerCase;
		upperCase = ex.toUpperCase();
		lowerCase = ex.toLowerCase();
		if(upperCase.equals(lowerCase.toUpperCase()))
			return true;
		return false;
	}
	
	
	public boolean accept(File file) {
		String[] extensions={"mp3","mp3","mp2","flv","avi","mkv","mpeg","mpg","divx","mov","dat","mpa","m4a","ogg","wav","3gp","srt",
				"wmv","ac3","wma","m4p","bup","aac","dts","gxf","m2v","m3u","m4v","ogm","pls","a52","dv","mov","vob","bin","ifo","part"
				,"3g2","rmvb","asf","wma","midi","mid","aif"};
	    if (file.isDirectory()) {
	      return true;
	    } else {
	      String path = file.getAbsolutePath().toLowerCase();
		for (int i = 0, n = extensions.length; i < n; i++) {
	        String extension = extensions[i];
	        if ((path.endsWith(extension) && (path.charAt(path.length() 
	                  - extension.length() - 1)) == '.')) {
	          return true;
	        }
	      }
	    }
	    return false;
	}
	
	
	boolean contenir(File file){
		for(int i = 0 ; i < liste_path_lect.size();i++){
			if(liste_path_lect.get(i).equalsIgnoreCase(file.getAbsolutePath()))
				return true;
		}
		return false;
	}
	
	private Object convertir(int duree) {
		// TODO Auto-generated method stub
		int heure=0;
		int minute=0;
		int seconde=duree;
		int jour = 0;
		if(duree>=60){
			minute=duree/60;
			seconde=seconde-minute*60;
		}
		if(minute>=60){
			heure=minute/60;
			minute=minute-heure*60;
		}
		if(heure>=24){
			jour=heure/24;
			heure=heure-jour*24;
		}
		String tps = " ";
		if(duree<3600){
			if(seconde<10)
				tps = minute+":0"+seconde;
			else
				tps = minute+":"+seconde;
		}
		else{
			if(minute<10 && seconde<10)
				tps = heure+":0"+minute+":0"+seconde;
			if(minute<10)
				tps = heure+":0"+minute+":"+seconde;
			if(seconde<10)
				tps = heure+":"+minute+":0"+seconde;
			else
				tps = heure+":"+minute+":"+seconde;
		}
			
		return tps;
	}
}

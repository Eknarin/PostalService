package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.NewDataSet;
import model.Table;
import controller.AustralianPostCode;
import controller.AustralianPostCodeSoap;
import controller.Postal;

/**
 * GUI of service
 * @author Eknarin 5510546239
 *
 */
public class PostalUI extends JFrame implements Runnable {
	
	/*
	 * ui components
	 */
	private JList<Table> answer;
	private JLabel header;
	private JLabel postal;
	private JLabel notFound;
	private JPanel topPanel;
	private JPanel mainPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JTextField txtInput;
	private JButton searchBtn;
	
	/*
	 * constructor
	 */
	public PostalUI() {
		super("Postal Service");
		initailizeComponent();
	}
	
	/*
	 * call all methods that create the ui
	 */
	public void initailizeComponent() {
		createComponent();
		createTopPanel();
		createMiddelPanel();
		createBottomPanel();
		createMainPanel();
	}

	/*
	 * create textField and button
	 */
	public void createComponent() {
		txtInput = new JTextField();
		postal = new JLabel("Postal Code : ");
		searchBtn = new JButton("SEARCH");
		
		searchBtn.addActionListener( new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Location location = new Location(txtInput.getText(),getUI());
				location.execute();
			}
		});
	}
	
	/*
	 * get the ui
	 */
	public PostalUI getUI() {
		return this;
	}
	
	/*
	 * pack the ui to get suitable size
	 */
	public void packResult(Table[] result) {
		answer = new JList<Table>( result );
		bottomPanel.add(answer);
		this.pack();
	}
	
	/*
	 * create a panel which collects all panels together
	 */
	public void createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout( mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(topPanel);
		mainPanel.add(middlePanel);
		mainPanel.add(bottomPanel);
		this.getContentPane().add(mainPanel);
	}
	
	/*
	 * panel which contains header
	 */
	private void createTopPanel() {
		header = new JLabel("Complete the District's name");
		topPanel = new JPanel();
		topPanel.add(header);
	}
	
	/*
	 * panel which contain textField and button for search
	 */
	public void createMiddelPanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout( middlePanel, BoxLayout.X_AXIS));
		middlePanel.add(txtInput);
		middlePanel.add(searchBtn);
		middlePanel.setPreferredSize(new Dimension(500, 30));
	}
	
	/*
	 * panel which has the pascode result
	 */
	public void createBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout( bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(postal);
	}
	
	@Override
	public void run() {
		this.pack();
		this.setVisible(true);
	}
}

/**
 * inner class
 * swingWorker
 * @author Eknarin 5510546239
 *
 */
class Location extends SwingWorker<Table[], Integer>
{
	private String district;
	private PostalUI ui;

	/*
	 * constructor
	 */
	public Location( String district, PostalUI ui) {
		this.district = district;
		this.ui = ui;
	}
	/*
	 * (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
    protected Table[] doInBackground() throws Exception
    {
    	Postal postal = new Postal();
		Table[] result = postal.findPostal(district);
		ui.packResult(result);
		return result;
    }
    /*
     * (non-Javadoc)
     * @see javax.swing.SwingWorker#done()
     */
    protected void done()
    {
        try
        {
             get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
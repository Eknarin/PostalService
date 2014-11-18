package org.ku.PostalService.view;

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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.WebServiceException;

import org.ku.PostalService.controller.AustralianPostCode;
import org.ku.PostalService.controller.AustralianPostCodeSoap;
import org.ku.PostalService.controller.Postal;
import org.ku.PostalService.model.NewDataSet;
import org.ku.PostalService.model.Table;


/**
 * GUI of service
 * 
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
	private JOptionPane alertPane;
	private Timer timer;
	private JProgressBar progressbar;

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
	 * create textField and button create timer
	 */
	public void createComponent() {
		txtInput = new JTextField();
		postal = new JLabel("Postal Code : ");

		progressbar = new JProgressBar(0, 100);
		progressbar.setValue(0);
		progressbar.setVisible(false);

		searchBtn = new JButton("SEARCH");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				progressbar.setVisible(true);
				Location location = new Location(txtInput.getText(), getUI(),
						timer);
				location.execute();
				// searchBtn.setEnabled(false);
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
		answer = new JList<Table>(result);
		// answer.setListData(result);
		bottomPanel.add(answer);
		this.pack();
	}

	/*
	 * create a panel which collects all panels together
	 */
	public void createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(topPanel);
		mainPanel.add(middlePanel);
		mainPanel.add(bottomPanel);
		mainPanel.add(progressbar);
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
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		middlePanel.add(txtInput);
		middlePanel.add(searchBtn);
		middlePanel.setPreferredSize(new Dimension(500, 30));
	}

	/*
	 * panel which has the pascode result
	 */
	public void createBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(postal);
	}

	@Override
	public void run() {
		this.pack();
		this.setVisible(true);
	}

	/**
	 * inner class swingWorker
	 * 
	 * @author Eknarin 5510546239
	 * 
	 */
	class Location extends SwingWorker<Table[], Integer> {
		private String district;
		private PostalUI ui;
		private Timer timer;
		Location location = this;

		/*
		 * constructor
		 */
		public Location(String district, PostalUI ui, Timer timer) {
			this.district = district;
			this.ui = ui;
			this.timer = timer;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.SwingWorker#doInBackground()
		 */
		protected Table[] doInBackground() throws Exception {

			Table[] result = null;

			timer = new Timer(12000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					location.cancel(true);
					String message = "It takes a long time to find the Result\n" 
									+ "Do you want to Retry or Exit?\n"
									+"Hint : You might take a mistake, Please recorrect the district";
					String caution = "Connection Timeout";
					int n = createAlert(message, caution);
					timer.stop();
					if (n == JOptionPane.YES_OPTION) {
						System.exit(0);
					} else {
						return;
					}
				}
			});
			
			result = findResult();
			 timer.stop();
			return result;
		}

		/*
		 * find the postal code of searching district
		 * @return list of postal code
		 */
		public Table[] findResult() {
			Table[] result = null;
				try {
					timer.start();
					Postal postal = new Postal();
					progressbar.setValue(25);
					result = postal.findPostal(district);
					progressbar.setValue(75);
					progressbar.setValue(100);
					 progressbar.setEnabled(false);
				} catch (WebServiceException e) {
					timer.stop();
					String message = "There are no Internet Connection\n"
									+ "Do you want to Retry or Exit?";
					String caution = "No Internet Connection";
					int n = createAlert(message, caution);
					if (n == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
					else{
						findResult();
					}
				}
				
			return result;
		}

		/*
		 * create aleart dialog
		 * Connection TimeOut and No Internet Connection
		 */
		public int createAlert(String message, String caution) {
			Object[] options = { "EXIT", "RETRY" };

			int n = JOptionPane.showOptionDialog(this.ui,message,caution,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[1]);
			return n;
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.SwingWorker#done()
		 */
		protected void done() {
			if (!location.isCancelled()) {
				try {
					ui.packResult(this.get());
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		}
	}
}

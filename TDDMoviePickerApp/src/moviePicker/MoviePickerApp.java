package moviePicker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
/**
 * 
 * @author charl
 *
 */
public class MoviePickerApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnGo;
	private JButton btnSeeMovies;
	private JButton btnGenerateRandomMovie;
	private JComboBox<String> genresDropdown;
	private JLabel lblYourMovieIs;
	private String[] genres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoviePickerApp frame = new MoviePickerApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MoviePickerApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panelTitle = createPanelTitle();
		contentPane.add(panelTitle);

		JPanel panelSearchByName = createPanelSearchByName();
		contentPane.add(panelSearchByName);

		MoviePicker model = new MoviePicker();
		genres = model.fetchAllGenres();
		
		JPanel panelGenres = createPanelGenres();
		contentPane.add(panelGenres);

		JPanel panelGenerateRandomMovie = createPanelGenerateRandomMovie();
		contentPane.add(panelGenerateRandomMovie);

		JPanel panelResults = createPanelResults();
		contentPane.add(panelResults);

		@SuppressWarnings("unused")
		Controller controller = new Controller(this, model);
	}

	private JPanel createPanelResults() {
		JPanel panelResults = new JPanel();
		panelResults.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblYourMovieIs = new JLabel("Your movie is waiting ...");
		lblYourMovieIs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelResults.add(lblYourMovieIs);
		return panelResults;
	}
	
	public void setPanelResults(String results) {
		this.lblYourMovieIs.setText(results);
	}

	private JPanel createPanelGenerateRandomMovie() {
		JPanel panelGenerateRandomMovie = new JPanel();
		panelGenerateRandomMovie.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnGenerateRandomMovie = new JButton("Generate Random Movie!");
		btnGenerateRandomMovie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelGenerateRandomMovie.add(btnGenerateRandomMovie);
		return panelGenerateRandomMovie;
	}
	
	public void addActionListenerToBtnGenerateRandomMovie(ActionListener listener) {
		this.btnGenerateRandomMovie.addActionListener(listener);
	}

	private JPanel createPanelGenres() {
		JPanel panelGenres = new JPanel();
		panelGenres.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblGenres = new JLabel("Genres:");
		lblGenres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelGenres.add(lblGenres);
		
		genresDropdown = new JComboBox<>();
		genresDropdown.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelGenres.add(genresDropdown);
		
		btnSeeMovies = new JButton("See Movies");
		btnSeeMovies.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelGenres.add(btnSeeMovies);
		for (String g : genres) {
			this.genresDropdown.addItem(g);
		}
		return panelGenres;
	}
	
	public void addActionListenerToBtnSeeMovies(ActionListener listener) {
		this.btnSeeMovies.addActionListener(listener);
	}
	
	public String getSelectedGenre() {
		return (String)this.genresDropdown.getSelectedItem();
	}

	private JPanel createPanelSearchByName() {
		JPanel panelSearchPanel = new JPanel();
		panelSearchPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblSearchByName = new JLabel("Search By Name:");
		lblSearchByName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelSearchPanel.add(lblSearchByName);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelSearchPanel.add(textField);
		textField.setColumns(20);
		
		btnGo = new JButton("Go");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelSearchPanel.add(btnGo);

		return panelSearchPanel;
	}
	
	public String getUserMovieInput() {
		return this.textField.getText();
	}
	
	public void addActionListenerToBtnGo(ActionListener listener) {
		this.btnGo.addActionListener(listener);
	}
	
	public void clearSearchTextField() {
		this.textField.setText("");
	}

	private JPanel createPanelTitle() {
		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JLabel lblMoviePicker = new JLabel("Movie Picker");
		lblMoviePicker.setBorder(new EmptyBorder(20, 10, 2, 10));
		lblMoviePicker.setForeground(Color.BLUE);
		lblMoviePicker.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		panelTitle.add(lblMoviePicker);

		return panelTitle;
	}

}

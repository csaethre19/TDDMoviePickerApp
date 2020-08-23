package moviePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	MoviePickerApp app;
	MoviePicker model;
	
	public Controller(MoviePickerApp app, MoviePicker model) {
		this.app = app;
		this.model = model;
		app.addActionListenerToBtnGo(new SearchMoviesListener());
		app.addActionListenerToBtnSeeMovies(new MoviesByGenreListener());
		app.addActionListenerToBtnGenerateRandomMovie(new GenerateRandomMovieListener());
	}
	
	class SearchMoviesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String movie = app.getUserMovieInput();
			if (!movie.equals("")) {
				String results = model.lookUpMovie(movie);
				app.setPanelResults(results);
				app.clearSearchTextField();
			}
		}
		
	}
	
	class MoviesByGenreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String genre = app.getSelectedGenre();
			String[] moviesByGenre = model.fetchMoviesByGenre(genre);
			genre = Character.toUpperCase(genre.charAt(0)) + genre.substring(1);
			String results = "<html>" + genre + " Movies:<br>" ;
			for (String movie : moviesByGenre) {
				results += movie + "<br>";
			}
			results += "</html>";
			app.setPanelResults(results);
		}
		
	}
	
	class GenerateRandomMovieListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String randomMovie = model.generateRandomMovie();
			String results = "Your Random Movie Pick: " + randomMovie;
			app.setPanelResults(results);
		}
		
	}
}

package moviePicker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoviePicker {
	private HashMap<String, String> movies = new HashMap<>();
	private String movie;
	private List<String> randomMovies = new ArrayList<>();
	private static int counter;
	private int lines = 0;
	
	public MoviePicker() {
		readMovies();
		movies.forEach((k,v) -> {
			randomMovies.add(k);
		});
		Collections.shuffle(randomMovies);
	}
	
	public void setUserMovieInquery(String movie) {
		this.movie = movie;
	}
	
	public String getUserMovieInquery() {
		return this.movie;
	}

	private void readMovies() {
		String nextMovie = null;
		try (BufferedReader bf = new BufferedReader(new FileReader("movies.txt"))) {
			while ((nextMovie = bf.readLine()) != null) {
				String[] movie = nextMovie.split(",");
				movies.put(movie[0], movie[1]);
				lines++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO - add functionality to look at a substring and compare parts of a string to movies in 
	// 		the list to find a match. {mermaid -> The Little Mermaid}
	public String lookUpMovie(String movie) {
		String movieKey = "";
		String genre = "";
		String movieInfo = "<html>";

		Set<String> keys = movies.keySet();
		for (String k : keys) {
			if (k.equalsIgnoreCase(movie)) {
				movieKey = k;
			}
		}
		if (movieKey.equalsIgnoreCase(movie)) {
			genre = movies.get(movieKey);
			movieInfo += "Title: " + movieKey + "<br>Genre: " + Character.toUpperCase(genre.charAt(0))
				+ genre.substring(1) + "</html>";
		}
		else {
			movieInfo = "Sorry your movie was not found.";
		}
		return movieInfo;
	}

	public String[] fetchAllGenres() {
		Set<String> values = new HashSet<>();
		movies.forEach((k,v) -> {
			values.add(v);
		});
		String[] genres = new String[values.size()];
		int i = 0;
		for (String value : values) {
			genres[i] = value;
			i++;
		}
		Arrays.sort(genres);
		return genres;
	}

	public String[] fetchMoviesByGenre(String genre) {
		List<String> moviesByGenre = new ArrayList<>();
		movies.forEach((k,v) -> {
			if (v.equals(genre)) {
				moviesByGenre.add(k);
			}
		});
		String[] result = new String[moviesByGenre.size()];
		int i = 0;
		for (String title : moviesByGenre) {
			result[i] = title;
			i++;
		}
		Arrays.sort(result);
		return result;
	}

	public String generateRandomMovie() {
		String randomMovie = "";
		if (counter < lines) {
			randomMovie = randomMovies.get(counter);
			counter++;
		}
		else {
			Collections.shuffle(randomMovies);
			counter = 0;
			randomMovie = randomMovies.get(counter);
		}

		return randomMovie;
	}

}

package moviePicker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MoviePicker {
	private HashMap<String, String> movies = new HashMap<>();
	private List<String> seenMovies = new ArrayList<>();
	private String movie;
	
	public MoviePicker() {
		readMovies();
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
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String lookUpMovie(String movie) {
		String movieKey = "";
		String genre = "";
		String movieInfo = "";

		Set<String> keys = movies.keySet();
		for (String k : keys) {
			if (k.equalsIgnoreCase(movie)) {
				movieKey = k;
			}
		}
		if (movieKey.equalsIgnoreCase(movie)) {
			genre = movies.get(movieKey);
			movieInfo = "Title: " + movieKey + "\nGenre: " + Character.toUpperCase(genre.charAt(0))
				+ genre.substring(1);
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

	//TODO FIX: When random number is the movie we are looking at but the movie was already seen
	// we return null instead of generating a new random number and returning a movie every time.
	public String generateRandomMovie() {
		Random rand = new Random();
		int num = rand.nextInt(16) + 1;
		Set<String> moviesToPick = movies.keySet();
		int i = 0;
		for (String m : moviesToPick) {
			if (i == num && !seenMovies.contains(m)) {
				seenMovies.add(m);
				return m;
			}
			i++;
		}
		return null;
	}

}

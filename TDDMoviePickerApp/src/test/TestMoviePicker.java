package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import moviePicker.MoviePicker;

class TestMoviePicker {
	MoviePicker moviePicker;
	
	@BeforeEach
	void setup_moviePickerInstance() {
		moviePicker = new MoviePicker();
	}
	
	@Test
	void test_receiveUserInputForMovie() {
		moviePicker.setUserMovieInquery("Matrix");
		String expected = "Matrix";
		String actualMovie = moviePicker.getUserMovieInquery();
		assertEquals(expected, actualMovie);
	}
	
	@Test
	void test_displayMovieTitleAndGenreOfFoundUserSelectedMovieAndMatchingLetterCase() {
		String movie = "The Lincoln Lawyer";
		String movieInfo = moviePicker.lookUpMovie(movie);
		String expected = "Title: The Lincoln Lawyer\nGenre: Drama";
		assertEquals(expected, movieInfo);
	}
	
	@Test
	void test_displayMovieTitleAndGenreOfFoundUserSelectedMovieAndMatchingLetterCase2() {
		String movie = "the neverending Story";
		String movieInfo = moviePicker.lookUpMovie(movie);
		String expected = "Title: The Neverending Story\nGenre: Fantasy";
		assertEquals(expected, movieInfo);
	}
	
	@Test
	void test_displayMovieTitleAndGenreOfFoundUserSelectedMovieAndNonMatchingLetterCase() {
		String movie = "matrix";
		String movieInfo = moviePicker.lookUpMovie(movie);
		String expected = "Title: Matrix\nGenre: Action";
		assertEquals(expected, movieInfo);
	}
	
	@Test
	void test_displayMessageWhenUserSelectedMovieIsNotFound() {
		String movie = "ferngully";
		String movieInfo = moviePicker.lookUpMovie(movie);
		String expected = "Sorry your movie was not found.";
		assertEquals(expected, movieInfo);
	}
	
	@Test
	void test_genresDisplayedToUserCorrectly() {
		String[] expected = {"action", 
							"anime",
							"drama", 
							"family", 
							"fantasy",
							"horror",
							"romance",
							"sci-fi"
							};
		String[] genres = moviePicker.fetchAllGenres();
		assertArrayEquals(expected, genres);
	}
	
	@Test
	void test_userSelectedGenreReturnsCorrectListOfMoviesWithThatGenre() {
		String genre = "action";
		String[] expectedMovies = {"Independence Day", "Matrix", "Men in Black"};
		String[] actualMovies = moviePicker.fetchMoviesByGenre(genre);
		assertArrayEquals(expectedMovies, actualMovies);
	}
	
	@Test
	void test_generateRandomMovieThatHasNotBeenSeenBefore() {
		String prevMovie = moviePicker.generateRandomMovie();
		String nextMovie = moviePicker.generateRandomMovie();
		assertTrue(!prevMovie.equals(nextMovie));
	}

}

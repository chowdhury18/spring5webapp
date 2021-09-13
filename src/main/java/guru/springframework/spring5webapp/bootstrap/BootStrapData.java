package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component: Spring automatically detect custom beans
 * Spring will scan the application for classes with @Component annotation
 * Instantiate them and inject any specific dependencies into them
 * Inject them based on needs
 */
@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	/**
	 * Dependency injection
	 * @param authorRepository
	 * @param bookRepository
	 */
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Author humayanAhmed = new Author("Humayan", "Ahmed");
		Book shabonMegherDinE = new Book("Shabon Megher Din e","123456");
		Book EiShobDinRaatri = new Book("Ei shob din raatri","123457");

		humayanAhmed.getBooks().add(shabonMegherDinE);
		humayanAhmed.getBooks().add(EiShobDinRaatri);
		shabonMegherDinE.getAuthors().add(humayanAhmed);
		EiShobDinRaatri.getAuthors().add(humayanAhmed);

		authorRepository.save(humayanAhmed);
		bookRepository.save(shabonMegherDinE);
		bookRepository.save(EiShobDinRaatri);

		Author Tagore = new Author("Rabindranath", "Tagore");
		Book GolpoGuchcho = new Book("Golpo Guchcho", "1234568");

		Tagore.getBooks().add(GolpoGuchcho);
		GolpoGuchcho.getAuthors().add(Tagore);

		authorRepository.save(Tagore);
		bookRepository.save(GolpoGuchcho);

		System.out.println("Started to Bootstrap");
		System.out.println("Number of Authors: " + authorRepository.count());
		System.out.println("Number of Books: " + bookRepository.count());
	}
}

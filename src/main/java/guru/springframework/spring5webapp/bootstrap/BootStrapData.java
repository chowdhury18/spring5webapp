package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
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
	private final PublisherRepository publisherRepository;

	/**
	 * Dependency injection
	 * @param authorRepository
	 * @param bookRepository
	 */
	public BootStrapData(AuthorRepository authorRepository,
						 BookRepository bookRepository,
						 PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Publisher sheba = new Publisher();
		sheba.setAddress("Banglabazar");
		sheba.setCity("Dhaka");
		publisherRepository.save(sheba);

		Author humayanAhmed = new Author("Humayan", "Ahmed");
		Book shabonMegherDinE = new Book("Shabon Megher Din e","123456");
		Book eiShobDinRaatri = new Book("Ei shob din raatri","123457");

		humayanAhmed.getBooks().add(shabonMegherDinE);
		humayanAhmed.getBooks().add(eiShobDinRaatri);
		shabonMegherDinE.getAuthors().add(humayanAhmed);
		eiShobDinRaatri.getAuthors().add(humayanAhmed);
		shabonMegherDinE.setPublisher(sheba);
		eiShobDinRaatri.setPublisher(sheba);
		sheba.getBooks().add(shabonMegherDinE);
		sheba.getBooks().add(eiShobDinRaatri);

		authorRepository.save(humayanAhmed);
		bookRepository.save(shabonMegherDinE);
		bookRepository.save(eiShobDinRaatri);
		publisherRepository.save(sheba);

		Author Tagore = new Author("Rabindranath", "Tagore");
		Book golpoGuchcho = new Book("Golpo Guchcho", "1234568");

		Tagore.getBooks().add(golpoGuchcho);
		golpoGuchcho.getAuthors().add(Tagore);
		golpoGuchcho.setPublisher(sheba);
		sheba.getBooks().add(golpoGuchcho);

		authorRepository.save(Tagore);
		bookRepository.save(golpoGuchcho);
		publisherRepository.save(sheba);

		System.out.println("Started to Bootstrap");
		System.out.println("Number of Authors: " + authorRepository.count());
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Number of Publisher: " + publisherRepository.count());
	}
}

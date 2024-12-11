package personal.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.literalura.model.Idioma;
import personal.literalura.model.Livro;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> findLivrosEmIdioma(Idioma idioma);

    @Query("SELECT l.idioma, COUNT(l) FROM Livro l GROUP BY l.idioma")
    List<Object[]> findQuantidadeDeLivrosPorIdioma();
}

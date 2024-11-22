package personal.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.literalura.model.Livro;

import java.util.List;
import java.util.Map;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> findLivrosEmIdioma(String idioma);

    @Query("SELECT l.idioma, COUNT(l) FROM Livro l GROUP BY l.idioma")
    //Map<String, Integer> f();
    List<Object[]> findQuantidadeDeLivrosPorIdioma();
}

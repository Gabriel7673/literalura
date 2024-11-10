package personal.literalura.principal;

import personal.literalura.model.Autor;
import personal.literalura.model.DadosGutendex;
import personal.literalura.model.Livro;
import personal.literalura.repository.AutorRepository;
import personal.literalura.repository.LivroRepository;
import personal.literalura.service.ConsumoAPI;
import personal.literalura.service.ConverteDados;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final String BASE_URL = "https://gutendex.com/books?";
    //private final String BASE_URL = "https://gutendex.com/books?search=";
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;


    public Principal(){}

    public Principal(LivroRepository repositorio){
        this.livroRepository = repositorio;
    }

    public void exibeMenu(){
        int opcao;
        String menu = """
                1) Pesquisar livro
                2) Listar livros registrados
                3) Listar autores registrados
                4) Listar autores vivos em dado ano
                5) Listar livros em um dado idioma
                0) Sair
                Opção: """;

        do {
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 0:
                    System.out.println("Até mais!");
                    break;
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listaAutoresVivosEmAno();
                    break;
                case 5:
                    listarLivrosEmIdioma();
                    break;
                default:
                    break;
            }

        } while (opcao != 0);

    }

    private List<Livro> getDados(String endereco){
        var json = consumoAPI.obterDados(endereco);

        // REFATORAR !!!
        DadosGutendex dados = converteDados.obterDados(json, DadosGutendex.class);
        return dados.listaDeLivros().stream()
                .map(l -> new Livro(l))
                .collect(Collectors.toList());
    }

    private void buscarLivro() {
        System.out.println("Digite um trecho do livro ou do autor desejado: ");
        var busca = leitura.nextLine();
        var endereco = BASE_URL + "search=" + busca.replace(" ", "%20");



        List<Livro> livrosBuscados = getDados(endereco);

        System.out.println("Resultados: ");
        livrosBuscados.forEach(System.out::println);

        // E se não encontrar o livro?
        System.out.println("Digite o código do livro desejado: ");
        var codigo = leitura.nextInt();
        leitura.nextLine();

        Livro livro = livrosBuscados.stream().
                filter(l -> l.getId()==codigo)
                .findFirst()
                .orElse(null);

        // E se for null?
        System.out.println(livro.toString());

        // livroRepository.save()

    }


    private void listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listaAutoresVivosEmAno(){
        System.out.println("Digite o ano desejado: ");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autorList = autorRepository.findAutoresVivosEmAno(ano);
        autorList.forEach(System.out::println);
    }

    private void listarLivrosEmIdioma(){
        String idiomas = """
                
                en - inglês
                pt - português
                """;

        System.out.println(idiomas);
        System.out.println("Digite o idioma desejado: ");
        var idioma = leitura.nextLine();

        List<Livro> livrosEmIdioma = livroRepository.findLivrosEmIdioma(idioma);
        livrosEmIdioma.forEach(System.out::println);
    }
}

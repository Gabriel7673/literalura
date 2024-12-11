package personal.literalura.principal;

import personal.literalura.model.Autor;
import personal.literalura.model.DadosGutendex;
import personal.literalura.model.Idioma;
import personal.literalura.model.Livro;
import personal.literalura.repository.AutorRepository;
import personal.literalura.repository.LivroRepository;
import personal.literalura.service.ConsumoAPI;
import personal.literalura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final String BASE_URL = "https://gutendex.com/books?search=";
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){
        int opcao;
        String menu = """
                
                
                -----------------------------------------------
                                     MENU
                -----------------------------------------------
                1) Pesquisar livro
                2) Listar livros registrados
                3) Listar autores registrados
                4) Listar autores vivos em dado ano
                5) Listar livros em um dado idioma
                6) Listar quantidade de livros por idioma
                0) Sair
                Opção: """;


        try {
            do {
                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();
                switch (opcao){
                    case 0:
                        System.out.println("Encerrando");
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
                    case 6:
                        listarQuantidadeDeLivrosPorIdioma();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (opcao != 0);

        } catch (InputMismatchException e){
            System.out.println("Opção inválida");
            leitura.nextLine();
        }

    }

    private List<Livro> getDados(String endereco){
        var json = consumoAPI.obterDados(endereco);

        DadosGutendex dados = converteDados.obterDados(json, DadosGutendex.class);
        return dados.listaDeLivros().stream()
                .map(l -> new Livro(l, autorRepository))
                .collect(Collectors.toList());
    }

    private void buscarLivro() {
        System.out.println("Digite um trecho do livro ou do autor desejado: ");
        var busca = leitura.nextLine();
        var endereco = BASE_URL + busca.replace(" ", "%20").strip();

        List<Livro> livrosBuscados = getDados(endereco);

        if (livrosBuscados.isEmpty()){
            System.out.println("Desculpe! Não houve resultados para a busca");
        }else {
            System.out.println("Resultados: ");
            livrosBuscados.forEach(System.out::println);

            try {
                System.out.println("Digite o código do livro desejado: (Caso o livro não esteja na lista, digite 0)");
                var codigo = leitura.nextInt();
                leitura.nextLine();

                if (codigo == 0){
                    System.out.println("Desculpe! Tente redefinir a busca");
                }else {
                    Livro livro = livrosBuscados.stream()
                            .filter(l -> l.getId() == codigo)
                            .findFirst()
                            .orElse(null);

                    if (livro == null) {
                        System.out.println(
                                "Não foi encontrado um livro com o código informado, verifique o código"
                        );
                    } else {
                        autorRepository.save(livro.getAutor());
                        livroRepository.save(livro);
                        System.out.println("Livro salvo com sucesso.");
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Desculpe! Código inválido");
                leitura.nextLine();
            }

        }
    }

    private void listarLivros() {
        System.out.println("\nLivros Resistrados: ");
        List<Livro> livros = livroRepository.findAll();
        imprimirLista(livros);
    }

    private void listarAutores(){
        System.out.println("\nAutores Resistrados: ");
        List<Autor> autores = autorRepository.findAll();
        imprimirLista(autores);
    }

    private void listaAutoresVivosEmAno(){
        try {
            System.out.println("Digite o ano desejado: ");
            var ano = leitura.nextInt();
            leitura.nextLine();

            System.out.printf("\nAutores vivos em %d: \n", ano);
            List<Autor> autorList = autorRepository.findAutoresVivosEmAno(ano);
            imprimirLista(autorList);
        }catch (InputMismatchException e){
            System.out.println("Desculpe! É necessário informar um ano");
            leitura.nextLine();
        }
    }

    private void listarLivrosEmIdioma(){
        String idiomas = """
          
                en - inglês
                pt - português
                fr - francês
                it - italiano
                es - espanhol
                """;

        System.out.println(idiomas);
        System.out.println("Digite o idioma desejado: ");
        var idiomaLido = leitura.nextLine();

        Idioma idioma;

        if (idiomaLido.length() == 2){
            idioma = Idioma.fromAbreviacao(idiomaLido);
        }else{
            idioma = Idioma.fromIdioma(idiomaLido);
        }

        if (idioma == Idioma.N0){
            System.out.println("Idioma inválido");
        }else {
            System.out.printf("\nLivros em %s: \n", idioma);
            List<Livro> livrosEmIdioma = livroRepository.findLivrosEmIdioma(idioma);
            imprimirLista(livrosEmIdioma);
        }
    }

    private void listarQuantidadeDeLivrosPorIdioma(){
        List<Object[]> list = livroRepository.findQuantidadeDeLivrosPorIdioma();
        list.forEach(arr -> {
            Idioma idioma = (Idioma) arr[0];
            Long quantidade = (Long) arr[1];
            System.out.println("Idioma: " + idioma + ", Quantidade: " + quantidade);
        });
    }

    private void imprimirLista(List lista){
        lista.forEach(System.out::println);
        if (lista.isEmpty()) System.out.println("Sem resultados");
    }
}

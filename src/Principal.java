/*****
 *
 * Programa de exemplo para construir uma interface gráfica utilizando
 * JavaFX.
 * O programa permitirá que o usuário digite o nome (ou qualquer outro texto)
 * e irá contar o número de vogais digitadas.
 *
 *****/


//importação das classes com os componentes
//note que os componentes gráficos pertencem ao
//pacote javafx. Tome cuidado ao fazer a importação
//pois você pode importar um componente de um pacote errado
//que irá lhe causar bastante transtorno
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//Toda aplicação JavaFx deve necessariamente
//possuir uma classe que extenda a superclasse Application do
//pacote javafx.
//Essa classe é reponsável para configurar o ambiente gráfico
public class Principal extends Application {



    public static void main(String[] args) {
        Application.launch(args);
    }


    //A classe deve necessariamente implementar o método abstrato
    //start. Ele será responsável por configurar o conteúdo da janela
    //principal da aplicação. Note que o método receber um objeto do tipo
    //Stage. Ele é a ligação entre a aplicação e a janela criada pelo
    //SO. Para que um componente seja visualizado, ele deve estar dentro de
    //um Stage. Uma aplicação possui somente um Stage.
    @Override
    public void start(Stage stage) throws Exception {

        //É necessária a existencia de pelo menos um Layout Manager para
        //organizar os componentes.
        //Para isso criamos um VBox para organizar os componentes na tela.
        //Os componentes serão organizados de forma vertical em uma coluna.

        VBox root = new VBox();

        //ajusta a cor de fundo do VBox para verde utilizando CSS
        root.setStyle("-fx-background-color: green");

        //cria espaço entre os componentes dentro do VBox
        root.setSpacing(5);
        //insere um espaço entre a borda da tela e o VBox
        root.setPadding(new Insets(5));

        //Cria um componente para identifcar a entrada de dados Nome
        Label lbNome = new Label("Nome");
        //Para um componente ser mostrado na tela ele deve ser incluído no
        //organizador de layout
        root.getChildren().add(lbNome);

        //Cria um campo de entrada de texto. Permite que o usuário digite texto
        //via teclado.
        TextField tfNome = new TextField();
        //Liga o label com o textfield
        lbNome.setLabelFor(tfNome);

        //insere o TextField no gerenciador de layout. Ele aparecerá abaixo
        //do Label
        root.getChildren().add(tfNome);

        //String auxiliar que irá mostrar o resultado da contagem.
        //Definimos uma porção {X} que será substituída
        String textoLabel = "O nome contém {X} vogais";

        //cria um componente de Texto, que será utilizado para mostrar o
        //resultado da contagem.
        //Incializamos o texto com a string auxiliar, substituindo a porção {X}
        //por 0.
        Text txtResultado = new Text(textoLabel.replace("{X}","0"));


        //Cria um componente do tipo Button, que é utilizado para receber
        //eventos de clique do usuário. Ele irá executar uma ação sempre que o
        //usuário clicar.
        Button botao = new Button("Contar");


        //inserimos o botão no organizador de layout
        root.getChildren().add(botao);

        //inserimos o componente de texto no organizador de layout. Veja
        //que deixamos ele por último, justamente para que ele seja o último
        //componente da coluna.
        root.getChildren().add(txtResultado);


        //As ações que serão realizadas pelo botão são definidas
        //no método setOnAction. Ele recebe como parâmetro um objeto
        //da interface EventHandler (neste caso estamos criando uma classe anônima).
        //A interface define o método handle, que será invocado toda vez que o botão
        //for clicado. Este é um ponto bastante importante. Essa porção do código só será
        //executada quando o botão é clicado.
        botao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Cliquei no botão...");
                //Lógica para contar o número de vogais do texto
                //que foi digitado pelo usuário

                //conjunto com as vogais
                HashSet<String> vogais = new HashSet<>(Arrays.asList("a","e","i","o","u"));


                //acessamos o texto digitado através do método getText do TextField.
                String nome = tfNome.getText();

                //lista que irá receber os caracteres do texto digitado
                ArrayList<String> caracteres = new ArrayList<>();

                //colocando os caracteres digitados na lista.
                //1 - transformamos a string nome para a versão com todas as letras
                //      minúsculas
                //2 - dividimos a string com o método split. Como não passamos nenhum separador
                //    será dividida por caracteres
                //3 - transformamos o vetor de caracteres em uma lista utilizando o método asList da classe
                //    Arrays
                //4 - inserimos todos os caracteres na lista
                caracteres.addAll(Arrays.asList(nome.toLowerCase().split("")));

                //utilizamos o método retainAll para fazer a intersecção
                //entre o conjunto das vogais e os caracteres digitados.
                //somente serão mantidos na lista os caracteres que existam nos dois
                //conjuntos
                caracteres.retainAll(vogais);

                //criamos uma string com o resultado utilizando a string auxiliar
                //e substituindo a porção {X} com o tamanho da lista, que indicará
                //o número de vogais do texto
                String resultado = textoLabel.replace("{X}",""+caracteres.size());

                //utlizamos o método setText do componente text para mostrar o resultado
                //na tela
                txtResultado.setText(resultado);
            }
        });


        //para que os componentes sejam mostrados na tela,
        //temos que criar uma cena. Na cena é definido o organizador
        //de layout raiz. Também é definido o tamanho da cena. Neste caso
        //estamos criando uma cena de 300x300 pixels.
        Scene scene = new Scene(root,300,300);

        //colocamos a cena no palco (stage).
        stage.setScene(scene);


        //invocamos o método show do palco, para que a cena
        //seja mostrada na janela.
        stage.show();

    }
}

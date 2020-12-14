package br.com.Servidor;

import br.com.Comum.Servidor;
import br.com.Comum.mensagem;

import java.util.*;

public class OServidor implements Servidor {

    private Map<Integer, String> sessao_nome = new HashMap<>();
    private Map<String, Integer> nome_sessao = new HashMap<>();
    private Map<Integer, List<Integer>> contatos = new HashMap<>();
    private Map<Integer, List<mensagem>> buffer = new HashMap<>();

    @Override
    public int autenticar(String nome) {

        int sessaoUsuario = getSessao();

        sessao_nome.put(sessaoUsuario, nome);
        nome_sessao.put(nome, sessaoUsuario);

        return sessaoUsuario;
    }

    @Override
    public int juntar(String nome, int sessao) {
        if (!sessao_nome.containsKey(sessao)){
            throw new RuntimeException("Sessão invalida");
        }

        if (!nome_sessao.containsKey(nome)){
            throw new RuntimeException(nome + "Não esta conectado");
        }

        List <Integer> misContatos = contatos.get(sessao);
            if (misContatos == null){
                misContatos = new LinkedList<>();
                contatos.put(sessao, misContatos);
            }

            misContatos.add(nome_sessao.get(nome));

            return nome_sessao.get(nome);
    }

    @Override
    public void enviar(String carta, int sessaoDe, int sessaoA) {
        if (!sessao_nome.containsKey(sessaoDe)){
            throw new RuntimeException("Sessão invalida");
        }

        if (!sessao_nome.containsKey(sessaoA)){
            throw new RuntimeException("Contato não está conectado");
        }

        if (!contatos.get(sessaoDe).contains(sessaoA)){
            throw new RuntimeException(sessao_nome.get(sessaoA) +
                    "Não é parte de contatos");
        }

        List<mensagem> mensagens = buffer.get(sessaoA);
        if (mensagens == null){
                mensagens = new LinkedList<>();
                buffer.put(sessaoA, mensagens);
        }

        mensagens.add(new mensagem(carta, sessao_nome.get(sessaoDe)));
        //possivel erro

    }

    @Override
    public List<mensagem> receber(int sessao) {
       if (!sessao_nome.containsKey(sessao)){
           throw new RuntimeException("Sessão invalida");
       }

       return buffer.get(sessao);

    }

    @Override
    public void limparBuffer(int sessao) {
        if (!sessao_nome.containsKey(sessao)){
            throw new RuntimeException("Sessão invalida");
        }

        buffer.get(sessao).clear();
    }

    public static int sessao = new Random().nextInt();

    public static int getSessao(){
        return ++sessao;
    }
}
